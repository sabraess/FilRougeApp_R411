package filrouge.app.main;

import java.util.List;

/*
* auteur : M.Rallo
* Interface qui permet de créer un objet PostExecuteActivity
*/


public interface PostExecuteActivity<T> {
    void onPostExecute(List<T> itemList); //retourne la liste des produits
    void runOnUiThread(Runnable runable); //permet de lancer une tâche en arrière plan
}
