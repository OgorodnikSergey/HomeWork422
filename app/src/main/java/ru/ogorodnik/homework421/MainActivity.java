package ru.ogorodnik.homework421;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Наш адаптер
    private ItemsDataAdapter adapter;
    // Список картинок, которые мы будем брать для нашего списка
    private List<Drawable> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        ListView listView = findViewById(R.id.listView);

        setSupportActionBar(toolbar);
        fillImages();

        // При тапе по кнопке добавим один новый элемент списка
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemData();
            }
        });

        // Создаем и устанавливаем адаптер на наш список
        adapter = new ItemsDataAdapter(this, null);
        listView.setAdapter(adapter);
        addItemData();

        // При тапе по элементу списка будем показывать его данные
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Тут мы получаем и отображаем данные,
                // но можно сделать и перейти в новую активити с этими данными
                showItemData(position);
            }
        });

        // При долгом тапе по элементу списка будем удалять его
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.removeItem(position);
                return true;
            }
        });

        //-------------------------------------------------------------
    }

    // Заполним различными картинками от домашних заданий
    // ContextCompat обеспечит нам поддержку старых версий Android
    private void fillImages() {
        images.add(ContextCompat.getDrawable(MainActivity.this,
                R.drawable.image121));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                R.drawable.image122));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                R.drawable.image211));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                R.drawable.image212));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                R.drawable.image221));
        images.add(ContextCompat.getDrawable(MainActivity.this,
                R.drawable.image311));
    }

    // Наполнение данными
    private void addItemData() {

        adapter.addItem(new ItemData(images.get(0), "1.2.1 Универсальная форма ввода", "Приложение “Hello world”"));
        adapter.addItem(new ItemData(images.get(1), "1.2.2 Бесконечный переход между экранами", "Приложение “Hello world”"));
        adapter.addItem(new ItemData(images.get(2), "2.1.1 Взаимоисключающие CheckBox", "Компоненты View. Иерархия Views”"));
        adapter.addItem(new ItemData(images.get(3), "2.1.2 Spinner «Страны-города-улицы»", "Компоненты View. Иерархия Views”"));
        adapter.addItem(new ItemData(images.get(4), "2.2.1 Записная книжка в SharedPreferences»", "Компоненты ViewGroup. SharedPrefs"));
        adapter.addItem(new ItemData(images.get(5), "3.1.1 Интерфейс калькулятора", "Верстка графического интерфейса в Android"));

    }

    // Покажем сообщение с данными
    private void showItemData(int position) {
        ItemData itemData = adapter.getItem(position);
        Toast.makeText(MainActivity.this,
                "Title: " + itemData.getTitle() + "\n" +
                        "Subtitle: " + itemData.getSubtitle(),
                Toast.LENGTH_SHORT).show();
    }
}