package com.technoecorp.gorilladealer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.technoecorp.gorilladealer.R;
import com.technoecorp.gorilladealer.bean.stateResponse.CountryBean;

import java.util.ArrayList;


/**
 * Created by Ruchi on 13-Nov-18.
 */

public class CountryListAdapter extends ArrayAdapter<CountryBean> {

    Context context;
    LayoutInflater inflate = null;
    private ArrayList<CountryBean> items;
    private ArrayList<CountryBean> itemsAll;
    private ArrayList<CountryBean> suggestions;

    public CountryListAdapter(Context context, int resID, int textviewId, ArrayList<CountryBean> items) {
        super(context, resID, textviewId, items);
        inflate = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
        this.itemsAll = (ArrayList<CountryBean>) items.clone();
        this.suggestions = new ArrayList<CountryBean>();
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
        holder.regimeData.setText("(" + items.get(position).getCountryDialCode() + ") - " + items.get(position).getCountryName());

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
            String str = ((CountryBean) (resultValue)).getCountryName();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (CountryBean product : itemsAll) {
                    if (product.getCountryName().toLowerCase()
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
            ArrayList<CountryBean> filteredList = (ArrayList<CountryBean>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (CountryBean c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }
        }
    };

}

