package app.aoyagi.makkan.iwamtproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.Source
import kotlinx.android.synthetic.main.activity_edit_idea.*

class EditIdeaActivity : AppCompatActivity() {
    val database = FirebaseFirestore.getInstance()
    var dataList : ArrayList<Any>? = null
    lateinit var queryDocument :QueryDocumentSnapshot


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_idea)

        fab.setOnClickListener {

            val idea_list = hashMapOf(
                "title" to titleText.text.toString(),
                "content" to content.text.toString(),
                "pourpose" to pourpose.text.toString()

            )


            getData()



            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }





    }
    fun getData(){
        database.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    //document.dataで中のデータが取ってこれた。
                    Log.d("asdfghjkl", "${document.id} => ${document.data}")
                    queryDocument = document
                    val item_data = queryDocument.data
                    dataList?.add(item_data)
                    //グローバル変数に入れて出力することで、汎用性を確保
                    Log.d("listresult", item_data.toString())
                }
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }
        return

    }
}

