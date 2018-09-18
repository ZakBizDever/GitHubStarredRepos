package com.hp5.gitrepos;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Zàk.J on 15/09/2018.
 */

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder>
{
    private List<Repository> reposList;
    private Context context;

    public RepositoryAdapter(List<Repository> reposList, Context context) {
        this.reposList = reposList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Repository repoItem = reposList.get(position);

        holder.repoName.setText(repoItem.getRepoName());
        holder.repoDescription.setText(repoItem.getRepoDescription());
        holder.repoOwnerName.setText(repoItem.getRepoOwnerName());
        holder.repoStarCount.setText(repoItem.getRepoStarsCount());

        Picasso.with(context).load(repoItem.getRepoOwnerAvatarUrl()).into(holder.repoOwnerAvatar);

        //Begin : Handling the click on an item
        holder.linLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, RepoShowActivity.class);
                intent.putExtra("repoURL",repoItem.getRepoURL());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

                Toast.makeText(context,"Repository : "+repoItem.getRepoName(),Toast.LENGTH_SHORT).show();

            }
        });
        //End

    }

    @Override
    public int getItemCount() {
        return reposList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView repoName;
        public TextView repoDescription;
        public TextView repoOwnerName;
        public TextView repoStarCount;
        public ImageView repoOwnerAvatar;

        public LinearLayout linLay;

        public ViewHolder(View itemView) {
            super(itemView);

            repoName = (TextView) itemView.findViewById(R.id.repoName_txt);
            repoDescription = (TextView) itemView.findViewById(R.id.repoDescription_txt);
            repoOwnerAvatar = (ImageView) itemView.findViewById(R.id.repoOwnerAvatar_img);
            repoOwnerName = (TextView) itemView.findViewById(R.id.repoOwnerName_txt);
            repoStarCount = (TextView) itemView.findViewById(R.id.repoStarCount_txt);
            linLay = (LinearLayout) itemView.findViewById(R.id.linLay);
        }
    }
}
