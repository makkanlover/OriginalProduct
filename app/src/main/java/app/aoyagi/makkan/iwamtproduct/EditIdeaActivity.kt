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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_idea)

        fab.setOnClickListener {
            sendData()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    fun sendData() {
        val ideaList = hashMapOf(
            "title" to titleText.text.toString(),
            "content" to content.text.toString(),
            "pourpose" to pourpose.text.toString()

        )

        database.collection("users")
            .add(ideaList)
            .addOnSuccessListener { documentReference ->
            }
            .addOnFailureListener { e ->
            }
    }


}

