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
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Генератор случайностей
    private Random random = new Random();
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
      //  generateRandomItemData();

        // При тапе по кнопке добавим один новый элемент списка
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateRandomItemData();
            }
        });

        // Создаем и устанавливаем адаптер на наш список
        adapter = new ItemsDataAdapter(this, null);
        listView.setAdapter(adapter);
        generateRandomItemData();

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



    }

    // Заполним различными картинками, которые встроены в сам Android
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

  //      images.add(ContextCompat.getDrawable(MainActivity.this,
  //              android.R.drawable.ic_menu_agenda));
  //      images.add(ContextCompat.getDrawable(MainActivity.this,
  //              android.R.drawable.ic_menu_camera));
  //      images.add(ContextCompat.getDrawable(MainActivity.this,
  //              android.R.drawable.ic_menu_call));
    }

    // Создадим ну почти случайные данные для нашего списка.
    // random.nextInt(граница_последнего_элемента)
    // Для каждого элемента мы возьмем 1 случайную картинку
    // из 5, которые мы сделали вначале.
    private void generateRandomItemData() {

        adapter.addItem(new ItemData(images.get(0), "1.2.1 Универсальная форма ввода", "Приложение “Hello world”", true));
        adapter.addItem(new ItemData(images.get(1), "1.2.2 Бесконечный переход между экранами", "Приложение “Hello world”", true));
        adapter.addItem(new ItemData(images.get(2), "2.1.1 Взаимоисключающие CheckBox", "Компоненты View. Иерархия Views”", true));
        adapter.addItem(new ItemData(images.get(3), "2.1.2 Spinner «Страны-города-улицы»", "Компоненты View. Иерархия Views”", true));
        adapter.addItem(new ItemData(images.get(4), "2.2.1 Записная книжка в SharedPreferences»", "Компоненты ViewGroup. SharedPrefs", true));
        adapter.addItem(new ItemData(images.get(5), "3.1.1 Интерфейс калькулятора", "Верстка графического интерфейса в Android", true));


//        adapter.addItem(new ItemData(
 //               images.get(random.nextInt(images.size())),
 //               "Hello" + adapter.getCount(),
 //               "It\'s me",
 //               random.nextBoolean()));
    }

    // Покажем сообщение с данными
    private void showItemData(int position) {
        ItemData itemData = adapter.getItem(position);
        Toast.makeText(MainActivity.this,
                "Title: " + itemData.getTitle() + "\n" +
                        "Subtitle: " + itemData.getSubtitle() + "\n" +
                        "Checked: " + itemData.isChecked(),
                Toast.LENGTH_SHORT).show();
    }
}