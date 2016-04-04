package udacity.finalproject.shows;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.PersonViewHolder>{

    private Context context;
    private List<Show>  shows;

    public RecyclerViewAdapter(Context context, List<Show>  shows){
        this.context = context;
        this.shows = shows;
    }

    @Override
    public int getItemCount() {
        return shows.size();
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PersonViewHolder holder, final int index){
        holder.mainImage.setImageResource(shows.get(index).getImageId());
        holder.name.setText(shows.get(index).getName());
        holder.genre.setText(shows.get(index).getGenre());
        holder.rating.setText(String.valueOf(shows.get(index).getRating()));

        holder.mainImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("OK", String.valueOf(index) + " " + String.valueOf(holder.mainImage.getId()));
                Intent intent = new Intent(context, InfoActivity.class);
                intent.putExtra(MainActivity.NUMBER, index);
                context.startActivity(intent);
            }
        });
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {

        private ImageView mainImage;
        private TextView name;
        private TextView genre;
        private TextView rating;

        public PersonViewHolder(final View view) {
            super(view);
            this.mainImage = (ImageView)view.findViewById(R.id.main_image);
            this.name = (TextView)view.findViewById(R.id.name);
            this.genre = (TextView)view.findViewById(R.id.genre);
            this.rating = (TextView)view.findViewById(R.id.rating);
        }
    }
}
