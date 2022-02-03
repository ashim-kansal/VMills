package com.vmotors.ui.tracking;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.vmotors.R;
import com.vmotors.domain.request.JobModel;
import com.vmotors.ui.order_details.OderDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.Viewholder> {

	private Context context;
	private List<JobModel> jobModelArrayList;

	// Constructor
	public JobAdapter(Context context, List<JobModel> jobModelArrayList) {
		this.context = context;
		this.jobModelArrayList = jobModelArrayList;
	}

	@NonNull
	@Override
	public JobAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		// to inflate the layout for each item of recycler view.
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_item, parent, false);
		return new Viewholder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull JobAdapter.Viewholder holder, int position) {
		// to set data to textview and imageview of each card layout
		JobModel model = jobModelArrayList.get(position);
		holder.courseNameTV.setText(model.getJob_name());
//		holder.courseRatingTV.setText("" + model.getCourse_rating());
//		holder.courseIV.setImageResource(model.getCourse_image());

		holder.llMain.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(context, OderDetailActivity.class);
				intent.putExtra("model", model);
				context.startActivity(intent);
			}
		});
	}

	@Override
	public int getItemCount() {
		// this method is used for showing number
		// of card items in recycler view.
		return jobModelArrayList.size();
	}

	// View holder class for initializing of
	// your views such as TextView and Imageview.
	public class Viewholder extends RecyclerView.ViewHolder {
		private CardView llMain;
		private TextView courseNameTV, courseRatingTV;

		public Viewholder(@NonNull View itemView) {
			super(itemView);
			llMain = itemView.findViewById(R.id.llMain);
			courseNameTV = itemView.findViewById(R.id.idTVCourseName);
//			courseRatingTV = itemView.findViewById(R.id.idTVCourseRating);

		}
	}
}
