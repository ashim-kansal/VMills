package com.vmotors.ui.select_firm;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.vmotors.R;
import com.vmotors.domain.request.Firm;
import com.vmotors.utils.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class FirmAdapter extends RecyclerView.Adapter<FirmAdapter.ViewHolder> implements Filterable {
	
	// creating a variable for array list and context.
	private ArrayList<Firm> firmArrayList ;
	private ArrayList<Firm> mainFirmsList ;
	private Context context;
	private OnItemClickListener onItemClickListener;

	// creating a constructor for our variables.
	public FirmAdapter(ArrayList<Firm> mFirmArrayList, Context context, OnItemClickListener onItemClickListener) {
		this.mainFirmsList = mFirmArrayList;
		this.firmArrayList = mFirmArrayList;
		this.context = context;
		this.onItemClickListener = onItemClickListener;
	}

	@NonNull
	@Override
	public FirmAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		// below line is to inflate our layout.
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.firm_rv_item, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull FirmAdapter.ViewHolder holder, int position) {
		// setting data to our views of recycler view.
		Firm modal = firmArrayList.get(position);
		holder.courseNameTV.setText(modal.getFirm_name());
		holder.courseDescTV.setText(modal.getAddress());
		holder.main.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.e("click", ""+modal.getFirm_name());
				onItemClickListener.onItemClick(modal);
			}
		});
	}

	@Override
	public int getItemCount() {
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
			main = itemView.findViewById(R.id.main);
		}
	}

	private Filter fRecords;

	@Override
	public Filter getFilter() {

		return new Filter() {
			@Override
			protected FilterResults performFiltering(CharSequence charSequence) {
				FilterResults results = new FilterResults();

				if (charSequence == null || charSequence.length() == 0) {
					results.values = mainFirmsList;
				} else {
					ArrayList<Firm> fRecords = new ArrayList<Firm>();

					for (Firm s : mainFirmsList) {
						if (s.getFirm_name().toUpperCase().trim().contains(charSequence.toString().toUpperCase().trim())) {
							fRecords.add(s);
						}
					}
					results.values = fRecords;
				}
				return results;
			}

			@Override
			protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
				firmArrayList = (ArrayList<Firm>) filterResults.values;
				notifyDataSetChanged();
			}
		};
	}

}
