package com.example.reahometime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.reahometime.adapter.HometimeAdapter
import com.example.reahometime.data.enum.StopId
import com.example.reahometime.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    var requestHometimeJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val hometimeNorthAdapter = HometimeAdapter()
        val hometimeSouthAdapter = HometimeAdapter()

        binding.lifecycleOwner = this

        binding.apply {

            viewModel = mainViewModel

            northList.apply {
                adapter = hometimeNorthAdapter
            }

            southList.apply {
                adapter = hometimeSouthAdapter
            }

            //bind clear button click listener
            setOnClearClicked {
                clearHometimes()
            }

            //bind load button click listener
            setOnLoadClicked {
                loadHometimes()
            }

        }

        subscribeUi(hometimeNorthAdapter, hometimeSouthAdapter)
    }

    /**
     * Observe livedata and submit list to adapters.
     */
    private fun subscribeUi(hometimeNorthAdapter: HometimeAdapter, hometimeSouthAdapter: HometimeAdapter) {
        mainViewModel.deviceToken.observe(this) { token ->
            if (!token.isNullOrEmpty()) {
                requestHometimes()
            }
        }

        mainViewModel.hometimesNorth.observe(this) { northList ->
            hometimeNorthAdapter.submitList(northList)
        }

        mainViewModel.hometimesSouth.observe(this) { southList ->
            hometimeSouthAdapter.submitList(southList)
        }
    }

    /**
     * When load button clicked
     * Start pulling job
     */
    private fun loadHometimes() {
        Toast.makeText(this, getString(R.string.start_load), Toast.LENGTH_SHORT).show()
        startPulling()
    }

    /**
     * When clear button clicked
     * Set both hometime lists as null and stop pulling
     */
    private fun clearHometimes() {
        Toast.makeText(this, getString(R.string.clear_time), Toast.LENGTH_SHORT).show()
        stopPulling()
        mainViewModel.clearHometimes()
    }

    /**
     * Request API for both north and south hometimes
     */
    private fun requestHometimes() {
        Toast.makeText(this, getString(R.string.loading), Toast.LENGTH_SHORT).show()
        mainViewModel.getHometimes(StopId.NORTH.value)
        mainViewModel.getHometimes(StopId.SOUTH.value)
    }

    /**
     * Start pulling job
     * This job will run every 5 seconds
     */
    private fun startPulling() {
        stopPulling()
        requestHometimeJob = lifecycleScope.launch {
            while (true) {
                requestHometimes()
                delay(INTERVAL_TIME)
            }
        }
    }

    /**
     * Stop pulling job
     */
    private fun stopPulling() {
        requestHometimeJob?.cancel()
        requestHometimeJob = null
    }

    companion object {
        const val INTERVAL_TIME = 10000L
    }
}