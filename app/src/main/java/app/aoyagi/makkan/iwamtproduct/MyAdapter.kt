package app.aoyagi.makkan.iwamtproduct

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


class MyAdapter(context: Context, layoutResourceId: Int, objects: List<IdeaData>) :
    ArrayAdapter<IdeaData>(context, layoutResourceId, objects) {
    var mIdeaDatas: List<IdeaData> = objects


    override fun getCount(): Int {
        return mIdeaDatas.size
    }

    override fun getItem(position: Int): IdeaData? {
        return mIdeaDatas[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val (viewHolder, convertView) = if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            val convertView = inflater.inflate(R.layout.view_expression, null)
            val viewHolder = ViewHolder(convertView)

            convertView.tag = viewHolder
            Pair(viewHolder, convertView)
        } else {
            Pair(convertView.tag, convertView)
        }

        val item: IdeaData? = getItem(position)

        if (item != null) {
            (viewHolder as ViewHolder).titleText.text = item.title_text
            (viewHolder as ViewHolder).heartImage.setOnClickListener {
                item.heart_count++
                viewHolder.heartCountText.text = (item.heart_count.toString())
            }
//            (viewHolder as ViewHolder).commentImage.setOnClickListener {
//                item.comment_count++
            viewHolder.commentCountText.text = (item.comment_count.toString())
//            }
        }
        return convertView
    }


    class ViewHolder(view: View) {
        val titleText: TextView = view.findViewById(R.id.titleText)
        val commentCountText: TextView = view.findViewById(R.id.comment_count_text)
        val heartCountText: TextView = view.findViewById(R.id.heart_count_text)
        val commentImage: ImageView = view.findViewById(R.id.comment_image)
        val heartImage: ImageView = view.findViewById(R.id.heart_image)
    }

}

