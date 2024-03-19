package filrouge.app;

/*
* interface qui permet de créer un objet Clickable
*/
public interface Clickable {
    void onClickItem(int itemPosition);
    void onRatingChanged(int itemPosition, float value);

}
