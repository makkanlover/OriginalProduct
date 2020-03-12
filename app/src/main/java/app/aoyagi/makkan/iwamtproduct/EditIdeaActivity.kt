package app.aoyagi.makkan.iwamtproduct

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import kotlinx.android.synthetic.main.activity_edit_idea.*

class EditIdeaActivity : AppCompatActivity() {
    private val database = FirebaseFirestore.getInstance()
    private val ideaData_list: ArrayList<IdeaData> = arrayListOf()
    lateinit var map: HashMap<String, String>
    private lateinit var queryDocument: QueryDocumentSnapshot
    private var map_title_list: ArrayList<String> = ArrayList()
    private var map_content_list: ArrayList<String> = ArrayList()
    private var map_pourpose_list: ArrayList<String> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_idea)

        fab.setOnClickListener {
            sendData()
            getData()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    fun sendData() {
        val idea_list = hashMapOf(
            "title" to titleText.text.toString(),
            "content" to content.text.toString(),
            "pourpose" to pourpose.text.toString()

        )

        database.collection("users")
            .add(idea_list)
            .addOnSuccessListener { documentReference ->
//                Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
//                Log.w("TAG", "Error adding document", e)
            }
    }


    fun getData() {
        database.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    //document.dataで中のデータが取ってこれた。
//                    Log.d("asdfghjkl", "${document.id} => ${document.data}")
                    queryDocument = document
                    val item_data = queryDocument.data
                    map = item_data as HashMap<String, String>
//                    map_title_list.add(map["title"].toString())
//                    map_content_list.add(map["contents"].toString())
//                    map_pourpose_list.add(map["pourpose"].toString())
//                    Log.d("123", map_title_list.toString())
                    ideaData_list.add(
                        IdeaData(
                            map["title"].toString(),
                            map["content"].toString(),
                            map["pourpose"].toString()
                        )
                    )

                }
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }
    }

}

