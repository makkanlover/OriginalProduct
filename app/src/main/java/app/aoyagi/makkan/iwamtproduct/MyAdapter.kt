package app.aoyagi.makkan.iwamtproduct

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore


class MyAdapter(
    private val context: Context,
    private var size :Int

) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    val database = FirebaseFirestore.getInstance()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.view_expression, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int  = database.hashCode()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleText.text = database.collection("users").document("title") .toString()
        holder.contentText.text = database.collection("users").document("contents") .toString()
        holder.pourposeText.text = database.collection("users").document("pourpose") .toString()


    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.expression_title)
        val contentText: TextView = view.findViewById(R.id.expression_contents)
        val pourposeText: TextView = view.findViewById(R.id.expression_pourpose)
    }

    fun getFirebaseData() {
        database.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("TAG", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }


    }

}

