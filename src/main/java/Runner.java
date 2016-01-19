import static spark.Spark.get;
import static spark.Spark.post;

public class Runner {

    public static void main(String[] args) {

        get("/", new HomeView());

        get("/login", new LoginView());
        post("/login", new LoginProcessor());

        get("/signup", new SignupView());
        post("/signup", new SignupProcessor());

//        signup



//        MongoClient client = new MongoClient();
//        MongoDatabase database = client.getDatabase("students");
//        MongoCollection<Document> grades = database.getCollection("grades");
//
//        /*
//        Write a program in the language of your choice that will remove the grade of type "homework" with the lowest
//        score for each student from the dataset that you imported in the previous homework. Since each document is one
//        grade, it should remove one document per student.
//
//        Hint/spoiler: If you select homework grade-documents, sort by student and then by score, you can iterate
//        through and find the lowest score for each student by noticing a change in student id. As you notice that
//        change of student_id, remove the document.
//         */
//
//
//        FindIterable<Document> found = grades.find()
//                .filter(and(eq("type", "homework")))
//                .sort(Sorts.ascending("student_id", "score"));
//
////        List<Document> toRemove = new ArrayList<>();
//
//        Integer currentStudentId = null;
//
//        for (Document document : found) {
//            if (!document.getInteger("student_id").equals(currentStudentId)) {
//                currentStudentId = document.getInteger("student_id");
//                grades.deleteOne(document);
////                toRemove.add(document);
//            }
//        }
//
////        System.out.println(toRemove.size());
    }
}
