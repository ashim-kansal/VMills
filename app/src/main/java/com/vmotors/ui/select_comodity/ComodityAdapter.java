package com.vmotors.ui.select_comodity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.vmotors.R;
import com.vmotors.domain.request.Firm;
import com.vmotors.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ComodityAdapter extends RecyclerView.Adapter<ComodityAdapter.ViewHolder> {

	// creating a variable for array list and context.
	private ArrayList<Firm> firmArrayList;
	private Context context;
	private OnItemClickListener onItemClickListener;

	// creating a constructor for our variables.
	public ComodityAdapter(List<Firm> firmArrayList, Context context, OnItemClickListener onItemClickListener) {
		this.firmArrayList = (ArrayList<Firm>) firmArrayList;
		this.context = context;
		this.onItemClickListener = onItemClickListener;
	}

	@NonNull
	@Override
	public ComodityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		// below line is to inflate our layout.
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.firm_rv_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull ComodityAdapter.ViewHolder holder, int position) {
		// setting data to our views of recycler view.
		Firm modal = firmArrayList.get(position);
		holder.courseNameTV.setText(modal.getCommodity());
		holder.main.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onItemClickListener.onItemClick(modal);
			}
		});

	}

	@Override
	public int getItemCount() {
		// returning the size of array list.
		return firmArrayList.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		
		// creating variables for our views.
		private TextView courseNameTV, courseDescTV;
		private CardView main;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			// initializing our views with their ids.
			courseNameTV = itemView.findViewById(R.id.idTVFirmName);
			courseDescTV = itemView.findViewById(R.id.idTVFirmLocation);
			courseDescTV.setVisibility(View.GONE);
			main = itemView.findViewById(R.id.main);

		}
	}
}
