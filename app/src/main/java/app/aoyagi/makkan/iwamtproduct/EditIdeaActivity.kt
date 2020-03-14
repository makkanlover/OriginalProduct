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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_idea)

        fab.setOnClickListener {
            sendData()
//            getData()
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
            }
            .addOnFailureListener { e ->
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
                    ideaData_list.add(
                        IdeaData(
                            map["title"].toString(),
                            map["content"].toString(),
                            map["pourpose"].toString()
                        )
                    )
                    Log.d("1234567u", map["title"].toString())
                }
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }
    }

}

