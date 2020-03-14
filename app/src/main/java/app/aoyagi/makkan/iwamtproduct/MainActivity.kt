package app.aoyagi.makkan.iwamtproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val database = FirebaseFirestore.getInstance()
    private var ideaDatas: ArrayList<IdeaData> = ArrayList()
    lateinit var map: HashMap<String, String>
    private lateinit var queryDocument: QueryDocumentSnapshot
    private var ideaDatasAdapter: MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()
        ideaDatasAdapter = MyAdapter(this, R.layout.view_expression, ideaDatas)
        list.adapter = ideaDatasAdapter
        intent_editActivity.setOnClickListener {
            val intent = Intent(this, EditIdeaActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun getData() {
        database.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    queryDocument = document
                    val item_data = queryDocument.data
                    map = item_data as HashMap<String, String>
                    ideaDatas.add(
                        IdeaData(
                            title_text = map["title"].toString(),
                            content_text = map["content"].toString(),
                            pourpose_text = map["pourpose"].toString()
                        )
                    )
                }
                ideaDatasAdapter!!.addAll(ideaDatas)
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }
    }


}
