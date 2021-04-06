package sample;

import com.google.gson.Gson;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javafx.scene.input.MouseEvent;
import sample.api.Key;
import sample.api.Weather;

public class Controller {
    @FXML
    private JFXButton send;

    @FXML
    private TextField input;

    @FXML
    private Text city;

    @FXML
    private Text description;

    @FXML
    private Text temp;

    @FXML
    private Text speed;

    @FXML
    private Text pressure;

    @FXML
    private Text humidity;

    @FXML
    void initialize() {
        assert send != null : "fx:id=\"send\" was not injected: check your FXML file 'sample.fxml'.";
        assert input != null : "fx:id=\"input\" was not injected: check your FXML file 'sample.fxml'.";
        assert city != null : "fx:id=\"city\" was not injected: check your FXML file 'sample.fxml'.";

    }

    @FXML
    void send_click(MouseEvent event) {
        String city_text = input.getText().trim().toLowerCase();
        if (city_text.length()>0){

            Weather weather = new Gson().fromJson(Api.getSender("http://api.openweathermap.org/data/2.5/weather?q="+city_text+"&appid="+ Key.weather_key +"&units=metric"), Weather.class);
            if (weather != null){
                city.setText(city_text);
                description.setText("description:\t" + weather.getWeather().get(0).getDescription());

                temp.setText("temp:\t\t" + weather.getMain().getTemp());

                speed.setText("wind speed:\t" + weather.getWind().getSpeed());

                pressure.setText("pressure:\t\t" + weather.getMain().getPressure());

                humidity.setText("humidity:\t\t" + weather.getMain().getHumidity());
            }else {
                    city.setText("Not found!");

                    description.setText("description:\t" + "!");

                    temp.setText("temp:\t\t" + "!");

                    speed.setText("wind speed:\t" + "!");

                    pressure.setText("pressure:\t\t" + "!");

                    humidity.setText("humidity:\t\t" + "!");

            }
        }else {
            city.setText("City names must be more than 5 letters");
        }

    }
}
