package com.mediageoloc.ata.user;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.mediageoloc.ata.R;
import com.mediageoloc.ata.utils.MediaGeolocContract.Users;
import com.squareup.picasso.Picasso;

public class UserSimpleAdapter extends SimpleCursorAdapter {
	
	Cursor myCursor;
	Context myContext;
	
	@InjectView(R.id.user_picture)
	ImageView userImageView;
	@InjectView(R.id.user_item)
	TextView userPseudo;
	
	public UserSimpleAdapter(Context context, int layout, Cursor c, String[] from,
			int[] to, int flags) {
		super(context, layout, c, from, to, flags);
		
		myCursor = c;
		myContext = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if(row == null){
			LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.user_item, parent, false); 
		}
		
		ButterKnife.inject(this, row);
		
		myCursor.moveToPosition(position);

		//pseudo
		String pseudo = myCursor.getString(myCursor.getColumnIndex(Users.COLUMN_NAME_PRENOM));
		userPseudo.setText(pseudo);
		
		//image
		String avatarPicUrl = myCursor.getString(myCursor.getColumnIndex(Users.COLUMN_NAME_TELEPHONE));
		Picasso.with(myContext).load(avatarPicUrl).into(userImageView);

		return row;
	}

	
	
}
