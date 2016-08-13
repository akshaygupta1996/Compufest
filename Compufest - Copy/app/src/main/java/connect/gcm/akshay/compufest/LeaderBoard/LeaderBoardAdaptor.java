package connect.gcm.akshay.compufest.LeaderBoard;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;

import java.util.List;

import connect.gcm.akshay.compufest.R;

public class LeaderBoardAdaptor extends RecyclerView.Adapter<LeaderBoardAdaptor.MyViewHolder> {


    private List<LeaderBoard> list;

    public LeaderBoardAdaptor(List<LeaderBoard> list)
    {
        this.list = list;
        Log.v("LIST",list.size()+"");
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView event_name,event_name1,first,second,third;
        FoldingCell foldingCell;

        public MyViewHolder(View itemView) {
            super(itemView);
            event_name=(TextView)itemView.findViewById(R.id.event_name);
            event_name1=(TextView)itemView.findViewById(R.id.event_name1);
            first=(TextView)itemView.findViewById(R.id.first);
            second=(TextView)itemView.findViewById(R.id.second);
            third=(TextView)itemView.findViewById(R.id.third);
            foldingCell=(FoldingCell)itemView.findViewById(R.id.folding_cell);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.leaderboard_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        LeaderBoard leaderBoard=list.get(position);
        holder.event_name.setText(leaderBoard.getEvent_name());
        holder.event_name1.setText(leaderBoard.getEvent_name());
        holder.first.setText(leaderBoard.getOne());
        holder.second.setText(leaderBoard.getTwo());
        holder.third.setText(leaderBoard.getThree());
       holder.foldingCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.foldingCell.toggle(false);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
