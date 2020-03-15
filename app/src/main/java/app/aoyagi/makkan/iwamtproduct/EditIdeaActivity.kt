package app.aoyagi.makkan.iwamtproduct

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_edit_idea.*

class EditIdeaActivity : AppCompatActivity() {
    private val database = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_idea)

        createButton.setOnClickListener {
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
            "pourpose" to pourpose.text.toString(),
            "heartcount" to "0",
            "timestamp" to FieldValue.serverTimestamp()

        )

        database.collection("users")
            .add(ideaList)
            .addOnSuccessListener { documentReference ->
            }
            .addOnFailureListener { e ->
            }
    }


}

