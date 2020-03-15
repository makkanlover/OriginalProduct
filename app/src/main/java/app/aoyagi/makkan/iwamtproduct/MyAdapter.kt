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


class MyAdapter(context: Context, layoutResourceId: Int, private var listener: OnItemClickListener
) :
    ArrayAdapter<IdeaData>(context, layoutResourceId) {
    private val inflater = LayoutInflater.from(context)
    private val database = FirebaseFirestore.getInstance()


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var (viewHolder, convertView) = if (convertView == null) {
            val convertView = inflater.inflate(R.layout.view_expression, null)
            val viewHolder = ViewHolder(convertView)


            convertView.tag = viewHolder
            Pair(viewHolder, convertView)
        } else {
            Pair(convertView.tag, convertView)
        }

        val item: IdeaData? = getItem(position)

        if (item != null) {
            val viewHolder: ViewHolder = viewHolder as ViewHolder
            viewHolder.titleText.text = item.title_text
            viewHolder.heartCountText.text = (item.heart_count.toString())
            var heartInt= item.heart_count.toInt()
            viewHolder.heartImage.setOnClickListener {
                heartInt++
                listener.onItemClick(heartInt,item.document_path)
                viewHolder.heartCountText.text = (heartInt.toString())
            }
            viewHolder.commentCountText.text = (item.comment_count.toString())
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

    interface OnItemClickListener {
        fun onItemClick(item: Int,id :String)
    }

}

