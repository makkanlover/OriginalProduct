package app.aoyagi.makkan.iwamtproduct

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import kotlinx.android.synthetic.main.activity_edit_idea.*

class EditIdeaActivity : AppCompatActivity() {
    val database = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_idea)


        fab.setOnClickListener {

            val idea_list = hashMapOf(
                "title" to titleText.text.toString(),
                "content" to content.text.toString(),
                "pourpose" to pourpose.text.toString()

            )
            database.collection("users")
                .add(idea_list)
                .addOnSuccessListener { documentReference ->
                    Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("TAG", "Error adding document", e)
                }
            val source = Source.CACHE

            val ideadata = database.collection("users")
            ideadata.get(source).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    Log.d("DAdafadsa", document.toString())

                }
            }


            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()


        }
    }
}
