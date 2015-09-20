package ahacraft.co.jp.gsapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.android.volley.Cache;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("TAG", "Hello world");

        getImageFromVolley();
    }

    public void getImageFromVolley(){
        String imageUrl = "http://www.aitia.co.jp/wordpress/wp-content/uploads/2015/01/xIT-image12-300x200.jpg";
        ImageView cover_imgView = (ImageView)findViewById(R.id.coverimageView);
        BitmapCache bmcache = new BitmapCache();
        RequestQueue rqQueue = Volley.newRequestQueue(this); ImageLoader ilImage = new ImageLoader(rqQueue, bmcache);
        Cache c = rqQueue.getCache();
        Cache.Entry b = c.get(imageUrl);
        if (b == null) {
            ImageLoader.ImageListener listener = ImageLoader.getImageListener(cover_imgView, android.R.drawable.ic_menu_rotate, android.R.drawable.ic_delete);
            ilImage.get(imageUrl, listener);
            Log.v("volley","2");
        } else {
            byte[] byteArray = b.data;
            Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            cover_imgView.setImageBitmap(bm);
            Log.v("volley","1");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
