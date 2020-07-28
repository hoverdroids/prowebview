package vcm.github.webkit.prowebview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import vcm.github.webkit.proview.ProWebView;
import vcm.github.webkit.proview.ProWebViewControls;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ProWebView webView;
    private EditText et;

    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webview);
        webView.setThirdPartyCookiesEnabled(true);
        webView.setCookiesEnabled(true);
        webView.setDesktopMode(true);
        webView.setEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setActivity(this);

        /*webView.addSpecialScheme("test", new ProWebView.SchemeRequest() {
            @Override
            public boolean onSchemeRequested(ProWebView view, String url) {
                view.loadHtml("<h1>Loaded scheme &quot;test&quot;</h1>", url, url, "utf-8");
                return true;
            }
        });*/

        webView.loadUrl("http://www.google.com");
        ProWebViewControls controls = findViewById(R.id.controls);
        controls.setProWebView(webView);

        et = findViewById(R.id.url);
        findViewById(R.id.load).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.markdown:
                webView.loadMarkdown("#Title\n" +
                        "##Title\n" +
                        "###Title\n" +
                        "####Title\n" +
                        "#####Title\n" +
                        "######Title\n" +
                        "Text with `code` here and a url [here](http://www.example.com/)\n" +
                        "\n" +
                        "~~Strike~~\n" +
                        "__Bold__\n" +
                        "*Italic*\n" +
                        "~~__*Strike, Bold and Italic*__~~\n" +
                        "\n" +
                        "```javascript\n" +
                        "function write(text) {\n" +
                        "\tconsole.log(text);\n" +
                        "}\n" +
                        "```\n" +
                        "\n" +
                        "![Markdown](https://upload.wikimedia.org/wikipedia/commons/thumb/4/48/Markdown-mark.svg/1200px-Markdown-mark.svg.png  \"Markdown\")");
                break;
            case R.id.html:
                webView.loadHtml("<!DOCTYPE html><html><h1>ProWebView</h1><p>This is a simple HTML code generated programmatically</p><p>See the documentation for more information</p></html>");
                break;
            case R.id.example:
                webView.loadUrl("https://www.google.com");
                break;
            case R.id.clear:
                webView.loadUrl("about:blank");
                break;
            case R.id.upload_test:
                webView.loadUrl("https://encodable.com/uploaddemo/");
                break;
            case R.id.custom_scheme:
                webView.loadUrl("test://scheme");
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        webView.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        webView.onRequestPermissionResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.onSavedInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        webView.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        String url = et.getText().toString();
        Log.d("Chris","Loading url:" + url);
        webView.loadUrl(url);
    }
}
