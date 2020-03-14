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
    private var mIdeaDatas: ArrayList<IdeaData> = ArrayList()
    lateinit var map: HashMap<String, String>
    private lateinit var queryDocument: QueryDocumentSnapshot
//    private var map_title_list: ArrayList<String> = ArrayList()
//    private var map_content_list: ArrayList<String> = ArrayList()
//    private var map_pourpose_list: ArrayList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mIdeaDatasAdapter: MyAdapter?
        getData()
        Log.d("checkData",mIdeaDatas.toString())
        mIdeaDatasAdapter = MyAdapter(this, R.layout.view_expression, mIdeaDatas)
        list.adapter = mIdeaDatasAdapter
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
//                    map_title_list.add(map["title"].toString())
//                    map_content_list.add(map["contents"].toString())
//                    map_pourpose_list.add(map["pourpose"].toString())
                    mIdeaDatas.add(
                        IdeaData(
                            title_text = map["title"].toString(),
                            content_text = map["content"].toString(),
                            pourpose_text = map["pourpose"].toString()
                        )
                    )

                }
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }
    }


}
