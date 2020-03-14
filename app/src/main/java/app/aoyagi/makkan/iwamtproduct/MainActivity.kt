package app.aoyagi.makkan.iwamtproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val database = FirebaseFirestore.getInstance()
    private val ideaDatas: ArrayList<IdeaData> = ArrayList()
    private lateinit var ideaDataAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ideaDataAdapter = MyAdapter(this, R.layout.view_expression)
        list.adapter = ideaDataAdapter
        getData()
        intentEditActivity.setOnClickListener {
            val intent = Intent(this, EditIdeaActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getData() {
        database.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val queryDocument = document
                    val itemData = queryDocument.data
                    val map = itemData as HashMap<String, String>
                    ideaDatas.add(
                        IdeaData(
                            title_text = map["title"].toString(),
                            content_text = map["content"].toString(),
                            pourpose_text = map["pourpose"].toString()
                        )
                    )
                }
                ideaDataAdapter.addAll(ideaDatas)
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }
    }


}
