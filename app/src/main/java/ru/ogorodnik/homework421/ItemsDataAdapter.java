package ru.ogorodnik.homework421;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 *  Адаптер - мост между данными и создаваемыми и управляемыми им View
 */
public class ItemsDataAdapter extends BaseAdapter {

    // Хранит список всех элементов списка
    private List<ItemData> items;
    Button buttonDel;

    // LayoutInflater – класс, который из
    // layout-файла создает View-элемент.
    private LayoutInflater inflater;

    // Конструктор, в который передается контекст
    // для создания контролов из XML. И первоначальный список элементов.
    ItemsDataAdapter(Context context, List<ItemData> items) {
        if (items == null) {
            this.items = new ArrayList<>();
        } else {
            this.items = items;
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // Добавляет элемент в конец списка.
    // notifyDataSetChanged сообщает об обновлении данных и переотрисовывает.
    // Вы можете как угодно менять items в других местах.
    // Но не забывайте вызывать notifyDataSetChanged чтобы все обновилось.
    void addItem(ItemData item) {
        this.items.add(item);
        notifyDataSetChanged();
    }

    // Удаляет элемент списка.
    void removeItem(int position) {
        items.remove(position);
        notifyDataSetChanged();
    }

    // Обязательный метод абстрактного класса BaseAdapter.
    // Он возвращает колличество элементов списка.
    @Override
    public int getCount() {
        return items.size();
    }

    // Тоже обязательный метод.
    // Должен возвращать элемент списка на позиции - position
    @Override
    public ItemData getItem(int position) {
        if (position < items.size()) {
            return items.get(position);
        } else {
            return null;
        }
    }

    // И это тоже обязательный метод.
    // Возвращает идентификатор. Обычно это position.
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Самый интересный обязательный метод.
    // Создает или возвращает переиспользуемый View, с новыми данными
    // для конкретной позиции. BaseAdapter – хитрый класс,
    // он не держит в памяти все View - это дорого и будет тормозить.
    // Поэтому он рисует только то что видно. Для этого есть convertView.
    // Если нет чего переиспользовать, то создается новый View.
    // А потом напоняет старую или новую View нужными данными.
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_list_view, parent, false);
        }

        ItemData itemData = items.get(position);

        ImageView image = view.findViewById(R.id.icon);
        TextView title = view.findViewById(R.id.title);
        TextView subtitle = view.findViewById(R.id.subtitle);
      final Button buttonDel = view.findViewById(R.id.buttonDel);
//-----------------------------------------------------------------------------------
// Повесил функцию удаления позиции во вьюшке при нажатии на кнопку "Удалить".
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                items.remove(position);
                notifyDataSetChanged();
            }
        });
//-----------------------------------------------------------------------------------
        image.setImageDrawable(itemData.getImage());
        title.setText(itemData.getTitle());
        subtitle.setText(itemData.getSubtitle());

        return view;
    }
}
