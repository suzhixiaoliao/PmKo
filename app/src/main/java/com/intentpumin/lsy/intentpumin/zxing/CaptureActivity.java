package com.intentpumin.lsy.intentpumin.zxing;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.intentpumin.lsy.intentpumin.DataExecuteTasksActivity;
import com.intentpumin.lsy.intentpumin.HelloChartActivity;
import com.intentpumin.lsy.intentpumin.R;
import com.intentpumin.lsy.intentpumin.tools.device.items;
import com.intentpumin.lsy.intentpumin.util.LightControl;
import com.pumin.lzl.pumin.Main_View;

import java.io.IOException;
import java.util.Vector;


/**
 * Initial the camera
 *
 * @author Ryan.Tang
 */
public class CaptureActivity extends Activity implements Callback {
    private Bundle bundle;
    private final String TAG = getClass().getSimpleName();
    private CaptureActivityHandler handler;
    private ViewfinderView viewfinderView;
    private boolean hasSurface;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private static final float BEEP_VOLUME = 0.10f;
    private boolean vibrate;
    private ImageView cancelScanButton;
    private Button btn_light_control;
    private boolean isShow = false;
    private ProgressBar pg;
    private ImageView iv_pg_bg_grey;
    private ImageView iv_big_circle;
    private ImageView iv_four_corner;
    private Button mBack;
    private items items;
    /**
     * Called when the activity is first created.
     */
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_diy);

        items = (com.intentpumin.lsy.intentpumin.tools.device.items) getIntent().getSerializableExtra("item");


        if (items != null) {
            Log.e(TAG, "收到了 ");
        }



        pg = (ProgressBar) findViewById(R.id.pg_camera_diy);
        iv_pg_bg_grey = (ImageView) findViewById(R.id.iv_camera_diy);
        iv_big_circle = (ImageView) findViewById(R.id.iv_camera_diy_circle);
        iv_four_corner = (ImageView) findViewById(R.id.iv_camera_diy_corner);
        CameraManager.init(getApplication());
        viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
        cancelScanButton = (ImageView) this.findViewById(R.id.btn_cancel_scan);
        btn_light_control = (Button) this.findViewById(R.id.btn_light_control);
        mBack = (Button) this.findViewById(R.id.btn_left);

        hasSurface = false;
        inactivityTimer = new InactivityTimer(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;

        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
        vibrate = true;

        //quit the scan view
        cancelScanButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                CaptureActivity.this.finish();
            }
        });
        btn_light_control.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                LightControl mLightControl = new LightControl();

                if (isShow) {
                    isShow = false;
                    btn_light_control.setBackgroundResource(R.mipmap.torch_off);
                    Toast.makeText(getApplicationContext(), "闪光灯关闭", Toast.LENGTH_LONG).show();
                    mLightControl.turnOff();
                } else {
                    isShow = true;
                    btn_light_control.setBackgroundResource(R.mipmap.torch_on);
                    mLightControl.turnOn();
                    Toast.makeText(getApplicationContext(), "闪光灯开启", Toast.LENGTH_LONG).show();
                }


            }
        });

        mBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    /**
     * Handler scan result
     *
     * @param result
     * @param barcode
     */
    public void handleDecode(Result result, Bitmap barcode) {
        inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        String resultString = result.getText();
        //FIXME
        if (resultString.equals("")) {
            Toast.makeText(CaptureActivity.this, "扫描失败!", Toast.LENGTH_SHORT).show();
        } else {
//			System.out.println("Result:"+resultString);
            if (pg != null && pg.isShown()) {
                pg.setVisibility(View.GONE);
                iv_pg_bg_grey.setVisibility(View.VISIBLE);
                iv_big_circle.setBackgroundResource(R.mipmap.bar_code_center_grey);
                iv_four_corner.setBackgroundResource(R.mipmap.bar_code_four_corner_grey);
            }
//			Intent resultIntent = new Intent(CaptureActivity.this, List_ZxingActivity.class);
            String s = getIntent().getStringExtra("str_all");
            if (s.equals("1")) {
                Intent it2 = new Intent(CaptureActivity.this, Main_View.class);
                it2.putExtra("put_equipment", resultString);
                startActivity(it2);
            }else {
                Intent resultIntent = this.getIntent().setClass(CaptureActivity.this, DataExecuteTasksActivity.class);
               // resultIntent.putExtra("item", items);
                resultIntent.putExtra("result", "" + resultString);
                startActivity(resultIntent);

            }
            CaptureActivity.this.finish();
        }

    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return;
        } finally {
            Log.e(TAG, "initCamera: 报错了");

        }
        if (handler == null) {
            handler = new CaptureActivityHandler(this, decodeFormats,
                    characterSet);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;

    }

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();

    }

    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(
                    R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }

    private static final long VIBRATE_DURATION = 200L;

    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }

    /**
     * When the beep has finished playing, rewind to queue up another one.
     */
    private final OnCompletionListener beepListener = new OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };
}