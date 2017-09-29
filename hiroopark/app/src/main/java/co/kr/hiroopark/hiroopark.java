package co.kr.hiroopark;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class hiroopark extends AppCompatActivity {
`
    private TextView mTextMessage;
    private WebView mWebView;
    private String TAG = "hiroopark";

    int year_x, month_x, day_x;
    static final int DIALOG_ID = 0;

    private String m_strLoadUrl = "http://www.hiroopark.co.kr/";
    private String m_strLoadDevUrl = "http://172.20.88.74/index.php?module=Default&action=DefaultMob/";
    private String m_strDefUrl = "http://172.20.88.74/index.php";
    private String m_strHomeUrl = "?module=Default&action=DefaultMob";
    private String m_strNavUrl1 = "?module=Html&action=SiteCompMob&sSubNo=4";
    private String m_strNavUrl2 = "?module=Board&action=SiteBoardMob&sMode=SELECT_FORM&iBrdNo=5";
    private String m_strNavUrl3 = "?module=Board&action=SiteBoardMob&sMode=SELECT_FORM&iBrdNo=6";
    private String m_strNavUrl4 = "?module=Inquiry&action=SiteInquiryMob&sMode=INSERT_FORM&iInquiryNo=1";

    private String m_strNavMenu = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_id01:
                    m_strNavMenu = m_strDefUrl + m_strHomeUrl;
                    break;
                case R.id.nav_id02:
                    m_strNavMenu = m_strDefUrl + m_strNavUrl1;
                    break;
                case R.id.nav_id03:
                    m_strNavMenu = m_strDefUrl + m_strNavUrl2;
                    break;
                case R.id.nav_id04:
                    m_strNavMenu = m_strDefUrl + m_strNavUrl3;
                    break;
                case R.id.nav_id05:
                    m_strNavMenu = m_strDefUrl + m_strNavUrl4;
                    break;
            }

            Log.v(TAG, "onNavigationItemSelected loadUrl : %s" + m_strNavMenu);

            mWebView.loadUrl(m_strNavMenu);
            return true;
        }

    };

    public void showDialog() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiroopark);

        mWebView = (WebView)findViewById(R.id.webView);
        mWebView.setWebViewClient(new WebViewClient());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DIALOG_ID);

            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        SetWebViewSettings();

        mWebView.loadUrl(m_strLoadDevUrl);

    }

    @Override
    protected Dialog onCreateDialog(int id) {

        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);

        Context context = new ContextThemeWrapper(this, android.R.style.Theme_Holo_Light_Dialog);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context = this;
        }

        if(id == DIALOG_ID)
            return new DatePickerDialog(this, dpickerListner, year_x, month_x, day_x);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListner
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMouth) {
            year_x = year;
            month_x = monthOfYear +1;
            day_x = dayOfMouth;

            Toast.makeText(hiroopark.this, year_x + "/" + month_x + "/" + day_x, Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onResume() {
        Log.v(TAG, "onResume ");

        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.v(TAG, "onRestart ");

        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.v(TAG, "onPause ");

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.v(TAG, "onDestroy ");

        super.onDestroy();
    }

    @Override
    protected void onStart() {
        Log.v(TAG, "onStart ");

        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.v(TAG, "onStop ");

        super.onStop();
    }

    @Override
    public void onBackPressed() {
        Log.v(TAG, "onBackPressed ");

        super.onBackPressed();
    }

    public void SetWebViewSettings () {
        Intent intent = getIntent();
        String param = intent.getDataString();

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setSupportMultipleWindows(true);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setAllowContentAccess(true);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        //mWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);

        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);

        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.setVerticalScrollBarEnabled(false);


        mWebView.setWebViewClient(new hirooparkWebViewClient());
        //mWebView.setWebChromeClient(new hirooparkWebChromClient());

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.v(TAG, "onKeyDown : %d" + keyCode);

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.v(TAG, "onKeyUp : %d" + keyCode);

        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        Log.v(TAG, "onKeyLongPress : %d" + keyCode);

        return super.onKeyLongPress(keyCode, event);
    }

    private class hirooparkWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.v(TAG, "onPageStarted : %s" + url);

            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Log.v(TAG, "onPageFinished : %s" + url);

            super.onPageFinished(view, url);
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            Log.v(TAG, "onLoadResource : %s" + url);

            super.onLoadResource(view, url);
        }



        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);

            switch (errorCode) {
                case ERROR_AUTHENTICATION:          //서버에서 사용자 인증 실패
                    break;
                case ERROR_BAD_URL:                 //잘못된 URL
                    break;
                case ERROR_CONNECT:                 //서버로 연결 실패
                    break;
                case ERROR_FAILED_SSL_HANDSHAKE:    //SSL handshake 수행
                    break;
                case ERROR_FILE:                    // 일반 파일 오류
                    break;
                case ERROR_FILE_NOT_FOUND:          // 파일을 찾을 수 없음
                    break;
                case ERROR_HOST_LOOKUP:             // 서버 또는 프록시 호스트 이름 조회 실패
                    break;
                case ERROR_IO:                      // 서버에서 읽거나 서버로 쓰기 실패
                    break;
                case ERROR_PROXY_AUTHENTICATION:    // 프록시에서 사용자 인증 실패
                    break;
                case ERROR_REDIRECT_LOOP:           // 너무 많은 리디렉션
                    break;
                case ERROR_TIMEOUT:                 // 연결 시간 초과
                    break;
                case ERROR_TOO_MANY_REQUESTS:       // 페이지 로드중 너무 많은 요청 발생
                    break;
                case ERROR_UNKNOWN:                 // 일반 오류
                    break;
                case ERROR_UNSUPPORTED_AUTH_SCHEME: // 지원되지 않는 인증 체계
                    break;
                case ERROR_UNSUPPORTED_SCHEME:      //URI가 지원되지 않는 방식
                    break;
            }

            Log.v(TAG, "onReceivedError : errorCode = %d" + errorCode + "description = %s" + description + "failingUrl = %s" +failingUrl);
        }

        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            Log.v(TAG, "onReceivedHttpError : request = %s" + request.toString() + " errorResponse = %s" + errorResponse);

            super.onReceivedHttpError(view, request, errorResponse);
        }

        @Override
        public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
            Log.v(TAG, "onUnhandledKeyEvent : %s" + event.getCharacters());

            super.onUnhandledKeyEvent(view, event);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.v(TAG, "shouldOverrideUrlLoading : %s" + url);

            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public boolean onRenderProcessGone(WebView view, RenderProcessGoneDetail detail) {
            return super.onRenderProcessGone(view, detail);
        }
    }



}
