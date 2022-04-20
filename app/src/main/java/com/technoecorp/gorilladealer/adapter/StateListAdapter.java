package com.technoecorp.gorilladealer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.technoecorp.gorilladealer.R;
import com.technoecorp.gorilladealer.bean.stateResponse.StateBean;

import java.util.ArrayList;


/**
 * Created by Ruchi on 13-Nov-18.
 */

public class StateListAdapter extends ArrayAdapter<StateBean> {

    Context context;
    LayoutInflater inflate = null;
    private ArrayList<StateBean> items;
    private ArrayList<StateBean> itemsAll;
    private ArrayList<StateBean> suggestions;

    public StateListAdapter(Context context, int resID, int textviewId, ArrayList<StateBean> items) {
        super(context, resID, textviewId, items);
        inflate = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
        this.itemsAll = (ArrayList<StateBean>) items.clone();
        this.suggestions = new ArrayList<StateBean>();
    }


    @Override
    public View getView(final int position, View rowView, ViewGroup parent) {
        final Holder holder;
        if (rowView == null) {
            holder = new Holder();
            rowView = inflate.inflate(R.layout.state_selected_item, null);
            holder.regimeData = rowView.findViewById(R.id.stateName);
            rowView.setTag(holder);
        } else {
            holder = (Holder) rowView.getTag();
        }
        holder.regimeData.setText(items.get(position).getStateName());

        return rowView;
    }
    public class Holder {
        TextView regimeData;
    }


    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        public String convertResultToString(Object resultValue) {
            String str = ((StateBean) (resultValue)).getStateName();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (StateBean product : itemsAll) {
                    if (product.getStateName().toLowerCase()
                            .startsWith(constraint.toString().toLowerCase())) {
                        suggestions.add(product);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            @SuppressWarnings("unchecked")
            ArrayList<StateBean> filteredList = (ArrayList<StateBean>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (StateBean c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }
        }
    };

}

