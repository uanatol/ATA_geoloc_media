package com.mediageoloc.ata.media.photo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.mediageoloc.ata.R;



/**
 * PhotoFilterPreviewActivity : Class for filter apply
 * @author Thierry
 *
 */
public class PhotoFilterPreviewActivity extends Activity {

	@InjectView(R.id.button_go_to_comment_preview) Button buttonGoToCommentPreview;
	@OnClick(R.id.button_go_to_comment_preview)
	public void saveAndGotoComment(Button button) {
		saveFilteredPictures();
		startCommentPreviewActivity();
		
	}
	
	@InjectView(R.id.check_box_filter) CheckBox filterAction;
	@OnClick(R.id.check_box_filter)
	public void applyFilter(CheckBox checkbox) {
		doFilter();
	}


	private Uri photoUri;
	private ImageView imageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_filter_preview);

		ButterKnife.inject(this);
		
		//display taken picture
		Intent intent = getIntent();
		imageView = (ImageView) findViewById(R.id.photo_preview);
		photoUri = Uri.parse(intent.getStringExtra("photoUri"));

		chargeImageSourcePreview();
	}


	private void chargeImageSourcePreview() {
		try {
			Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),photoUri);
			bitmap = Bitmap.createScaledBitmap(bitmap, 140, 190, false);
			imageView.setImageBitmap(bitmap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private void startCommentPreviewActivity(){
		
		Intent intent = new Intent(this, PhotoCommentPreviewActivity.class);
		intent.putExtra("photoUri", photoUri.toString());
		startActivity(intent);
	}

	private void doFilter(){
		if (filterAction.isChecked()){
			
			int value=100;

			BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
			final Bitmap src = drawable.getBitmap();
			Bitmap bmOut = PhotoUtils.brightnessFilter(value, src);
			
			imageView.setImageBitmap(bmOut);
		}
		else{
			chargeImageSourcePreview();
		}
	}
/**
 * saveFilteredPictures : save pictured filtered on same pathfile if filter apply
 *						 		
 */
	private void saveFilteredPictures() {
		//if need
		if (filterAction.isChecked()){
			
			try {
				//BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
				//final Bitmap src = drawable.getBitmap();
				Bitmap src = MediaStore.Images.Media.getBitmap(this.getContentResolver(),photoUri);
				
				String pathFile=photoUri.getPath();
				
				
				File file = new File(pathFile); 
				OutputStream out = new FileOutputStream(file); 
				
			    src.compress(Bitmap.CompressFormat.JPEG, 100, out); 
	            out.flush(); 
	            out.close();

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	



}
