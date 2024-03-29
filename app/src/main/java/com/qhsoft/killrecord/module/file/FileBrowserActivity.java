package com.qhsoft.killrecord.module.file;

import android.Manifest;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.qhsoft.killrecord.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileBrowserActivity extends ListActivity {
	private List<Map<String, Object>> mData;
	private String mDir = "/sdcard";
	private int index;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getPermissions();

	}
    private void getPermissions(){
		if (ContextCompat.checkSelfPermission(FileBrowserActivity.this,
				Manifest.permission.WRITE_EXTERNAL_STORAGE)
				!= PackageManager.PERMISSION_GRANTED) {


			if (ActivityCompat.shouldShowRequestPermissionRationale(FileBrowserActivity.this,
					Manifest.permission.WRITE_EXTERNAL_STORAGE)) {


			} else {

				ActivityCompat.requestPermissions(FileBrowserActivity.this,
						new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
						0);


			}
		}else {
			initView();
		}

    }
	@Override
	public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
		switch (requestCode) {
			case 0: {

				if (grantResults.length > 0&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					//同意
					initView();


				} else {//被拒绝

				}
				return;
			}


		}
	}

    private void initView() {
        Intent intent = this.getIntent();
        Bundle bl = intent.getExtras();
        String title = bl.getString("explorer_title");
        Uri uri = intent.getData();
        mDir = uri.getPath();
        index=intent.getIntExtra("index", 0);
        setTitle(title);
        mData = getData();
        MyAdapter adapter = new MyAdapter(this);
        setListAdapter(adapter);

        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();
        LayoutParams p = getWindow().getAttributes();
        p.height = (int) (d.getHeight());
        p.width = (int) (d.getWidth());
        getWindow().setAttributes(p);
    }

    private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		File f = new File(mDir);
		File[] files = f.listFiles();

		if (!mDir.equals(Environment.getExternalStorageDirectory().getAbsolutePath())) {
			map = new HashMap<String, Object>();
			map.put("title", "上一级../");
			map.put("info", f.getParent());
			map.put("img", R.drawable.ex_folder);
			list.add(map);
		}
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				map = new HashMap<String, Object>();
				map.put("title", files[i].getName());
				map.put("info", files[i].getPath());
				if (files[i].isDirectory())
					map.put("img", R.drawable.ex_folder);
				else
					map.put("img", R.drawable.ex_doc);
				list.add(map);
			}
		}
		return list;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Log.d("MyListView4-click", (String) mData.get(position).get("info"));
		if ((Integer) mData.get(position).get("img") == R.drawable.ex_folder) {
			mDir = (String) mData.get(position).get("info");
			mData = getData();
			MyAdapter adapter = new MyAdapter(this);
			setListAdapter(adapter);
		} else {
			finishWithResult((String) mData.get(position).get("info"));
		}
	}

	public final class ViewHolder {
		public ImageView img;
		public TextView title;
		public TextView info;
	}

	public class MyAdapter extends BaseAdapter {
		private LayoutInflater mInflater;

		public MyAdapter(Context context) {
			this.mInflater = LayoutInflater.from(context);
		}

		public int getCount() {
			return mData.size();
		}

		public Object getItem(int arg0) {
			return null;
		}

		public long getItemId(int arg0) {
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.layout_listview_item, null);
				holder.img = (ImageView) convertView.findViewById(R.id.img);
				holder.title = (TextView) convertView.findViewById(R.id.title);
				holder.info = (TextView) convertView.findViewById(R.id.info);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.img.setBackgroundResource((Integer) mData.get(position).get(
					"img"));
			holder.title.setText((String) mData.get(position).get("title"));
			holder.info.setText((String) mData.get(position).get("info"));
			return convertView;
		}
	}

	private void finishWithResult(String path) {
		Bundle conData = new Bundle();
		conData.putString("results", "Thanks Thanks");
		Intent intent = new Intent();
		intent.putExtras(conData);
		Uri startDir = Uri.fromFile(new File(path));
		intent.setDataAndType(startDir,"vnd.android.cursor.dir/lysesoft.andexplorer.file");
		intent.putExtra("index", index);
		setResult(RESULT_OK, intent);



		finish();
	}



};