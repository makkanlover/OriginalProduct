package app.aoyagi.makkan.iwamtproduct

data class IdeaData(
    var title_text: String,
    var content_text: String,
    var pourpose_text: String,
    var document_path:String,
    var heart_count: String,
    var comment_count: Int = 0
)