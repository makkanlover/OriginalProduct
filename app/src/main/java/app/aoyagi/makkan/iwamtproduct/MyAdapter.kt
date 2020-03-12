package app.aoyagi.makkan.iwamtproduct

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


class MyAdapter(
    context: Context,
    layoutResourceId: Int,
    objects: List<IdeaData>
) : ArrayAdapter<IdeaData>(context,layoutResourceId,objects) {
    var mIdeaDatas: List<IdeaData> = objects


    override fun getCount(): Int {
        return mIdeaDatas.size
    }



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.expression_title)
        val contentText: TextView = view.findViewById(R.id.expression_contents)
        val pourposeText: TextView = view.findViewById(R.id.expression_pourpose)
//        val heartText :TextView = view.findViewById()
    }

}

