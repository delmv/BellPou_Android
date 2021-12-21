package com.henallux.bellpou.view.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.henallux.bellpou.R
import com.henallux.bellpou.viewmodel.LoadingActivityViewModel
import com.henallux.bellpou.viewmodel.ScanQRViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QRScanActivity : AppCompatActivity() {

    private lateinit var codeScanner: CodeScanner
    private lateinit var viewModel: ScanQRViewModel;


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrscan)

        viewModel = ViewModelProvider(this).get(ScanQRViewModel::class.java)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 123)
        else
            startScanning()

    }

    private fun startScanning() {

        val scanner: CodeScannerView = findViewById(R.id.scanner)

        codeScanner = CodeScanner(this, scanner)
        codeScanner.camera = CodeScanner.CAMERA_BACK
        codeScanner.formats = CodeScanner.ALL_FORMATS

        codeScanner.autoFocusMode = AutoFocusMode.SAFE
        codeScanner.scanMode = ScanMode.SINGLE
        codeScanner.isAutoFocusEnabled = true
        codeScanner.isFlashEnabled = false

        codeScanner.decodeCallback = DecodeCallback {

            CoroutineScope(Dispatchers.IO).launch {

                try {

                    viewModel.reportTrash(it.text)

                    withContext(Dispatchers.Main) {

                        val toast = Toast.makeText(this@QRScanActivity, getString(R.string.trash_signaled), Toast.LENGTH_SHORT)
                        toast.show()

                    }


                } catch (e: Exception) {

                    withContext(Dispatchers.Main) {

                        val toast = Toast.makeText(this@QRScanActivity, e.message, Toast.LENGTH_SHORT)
                        toast.show()

                    }

                } finally {

                    val intent = Intent(this@QRScanActivity, LoggedActivity::class.java)
                    this@QRScanActivity.startActivity(intent)

                }

            }

        }

        codeScanner.errorCallback = ErrorCallback {

            it.message?.let { it -> Log.w("QR Scan Activity", it) }

            Toast.makeText(this, getString(R.string.camera_initialization_failed), Toast.LENGTH_SHORT).show()

            val intent = Intent(this@QRScanActivity, LoggedActivity::class.java)
            this@QRScanActivity.startActivity(intent)

        }

        scanner.setOnClickListener {

            codeScanner.startPreview()

        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 123) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, getString(R.string.camera_permission_granted), Toast.LENGTH_SHORT).show()
                startScanning()

            } else {

                Toast.makeText(this, getString(R.string.camera_permission_denied), Toast.LENGTH_SHORT).show()

                val intent = Intent(this@QRScanActivity, LoggedActivity::class.java)
                this@QRScanActivity.startActivity(intent)

            }

        }

    }

    override fun onResume() {

        super.onResume()

        if (::codeScanner.isInitialized)
            codeScanner.startPreview()

    }

    override fun onPause() {

        if (::codeScanner.isInitialized)
            codeScanner.releaseResources()

        super.onPause()
    }
}