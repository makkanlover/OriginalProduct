package app.aoyagi.makkan.iwamtproduct

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_comment.*
import kotlinx.android.synthetic.main.activity_edit_idea.*


class CommentActivity : AppCompatActivity() {
    private val database = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
        val documentId = intent.getStringExtra("id")
        val document = database.collection("users").document(documentId)
        document.get()
            .addOnSuccessListener {document ->
                if (document != null) {
                    val queryDocument = document
                    val itemData = queryDocument.data
                    val map = itemData as HashMap<String, String>
                    pourposeText.text = map["pourpose"].toString()
                    contentText.text = map["content"].toString()
                    heartCount.text = map["heartcount"].toString()
                } else {
                }
            }

    }
}