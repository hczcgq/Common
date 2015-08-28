package com.chen.ui;

import com.chen.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ProgressBar;

public class AsyncTaskActivity extends Activity {

    private ProgressBar mProgressBar;

    private TestAsyncTask mTask;
    
    private ViewStub importPanel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_asynctask);

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        importPanel=(ViewStub) findViewById(R.id.stub_import);
        importPanel.inflate();

        mTask = new TestAsyncTask();
        mTask.execute();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mTask != null && mTask.getStatus() == AsyncTask.Status.RUNNING) {
            mTask.cancel(true);
        }
    }

    class TestAsyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... paramVarArgs) {

            for (int i = 0; i < 100; i++) {
                if (isCancelled()) {
                    break;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mProgressBar.setProgress(values[0]);
            if (isCancelled()) {
                return;
            }
        }
        
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            importPanel.setVisibility(View.GONE);
        }
        
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            importPanel.setVisibility(View.VISIBLE);
        }
    }
}
