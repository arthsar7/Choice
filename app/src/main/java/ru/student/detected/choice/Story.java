package ru.student.detected.choice;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Story {
    private final TextView[] options_v;
    private final ImageView[] cards;
    private final TextView outcome;
    private final Context context;
    private final ImageView image;
    public Situation current_situation;
    public Situation story_ending, death_baron, root, bad, good;
    Situation.Option[] options = {new Situation.Option("Согласиться", 0, 1, true),
            new Situation.Option("Послать", 0, -1, true),
            new Situation.Option("Вызвать на бой", 0, -2, true)};
    Situation start_story = new Situation("Первая встреча", "Вы встречаете в " +
            "деревне язвительного мужчину в красных доспехах, " +
            "прозванного Кровавым Бароном. Он просит Вас найти его семью, жену Анну и дочь Тамару.", R.drawable.first, options);
    public Story(Context context, ImageView image, TextView[] options_v, ImageView[] cards, TextView outcome) {
        this.options_v = options_v;
        this.cards = cards;
        this.outcome = outcome;
        this.context = context;
        this.image = image;


        Situation.Option none = new Situation.Option("", 0, 0, false);
        Situation.Option[] endOptions = {none, new Situation.Option("Начать заново", 0, 0, true), none};
        Situation start_story = new Situation("Первая встреча", "Вы встречаете в " +
                "деревне язвительного мужчину в красных доспехах, " +
                "прозванного Кровавым Бароном. Он просит Вас найти его семью, жену Анну и дочь Тамару.", R.drawable.first, options);
        Situation.Option[] options1 = {new Situation.Option("Осмотреть комнату жены", 0, 0, true),
                new Situation.Option("Спросить про отношения в семье", 0, 0, true),
                new Situation.Option("Осмотреть комнату дочери", 0, 0, true)};
        Situation.Option[] options1_1 = {none, new Situation.Option("Рассказать барону о случившемся", 0, 1, true), none};
        Situation.Option[] options1_1_1 = {none, new Situation.Option("Отправиться к ворожею", 0, 0, true), none};
        Situation.Option[] options1_1_1_2 = {none, new Situation.Option("Как найти Анну?", 0, 0, true), none};
        Situation.Option[] options1_1_1_2_2 = {new Situation.Option("Мне надо узнать, где игоша", 0, 0, true), new Situation.Option("Как снять проклятие?", 0, 0, true), none};
        Situation.Option[] options1_2 = {new Situation.Option("Поддержать", 0, 2, true),
                new Situation.Option("\"Ну ты и тряпка..\"", 0, -2, true),
                new Situation.Option("Вернуться к уликам", 0, 0, true)};
        Situation.Option[] options1_2_2 = {none, new Situation.Option("Вернуться к уликам", 0, 0, true), none};
        Situation.Option[] options1_3 = {none, new Situation.Option("Вернуться назад", 0, 0, true), none};
        Situation.Option[] options2 = {none, new Situation.Option("\"Я передумал, идем\"", 0, 1, true),
                new Situation.Option("Оскорбиться жестом и атаковать", 0, -1, true)};
        Situation.Option[] options3 = {new Situation.Option("Попытаться вырубить всех голыми кулаками", -100, -3, true),
                new Situation.Option("Убить стальным мечом", -70, -3, true), none};

        story_ending = new Situation("Геральт умер","Вы мертвы", R.drawable.dead, endOptions);
        death_baron = new Situation("Барон мертв","Вы перебили половину замка и убили барона, вас ненавидят" +
                " и прогоняют везде, где вы появляетесь", R.drawable.deadbaron, endOptions);
        bad = new Situation("Плохая репутация","Ворожеем овладевают владыки леса" +
                " и он убивает себя", R.drawable.prediction, endOptions);
        good = new Situation("Хорошая репутация","Ворожей сообщает, что игоша поможет отыскать Анну, " +
                "потому что «кровь всегда родную кровь найдет». Он добавляет, что игошу нужно искать в полночь у пустой могилы, " +
                "а где захоронение должен знать барон.\n Продолжение следует...", R.drawable.prediction, endOptions);
        start_story.way[0] = new Situation("Согласиться", "Кровавый Барон обещает " +
                "Вам сведения о Цири и провожает в комнату пропавшей семьи", R.drawable.room, options1);
        start_story.way[0].way[0] =new Situation("Комната Анны", "Использовав ведьмачье чутье," +
                "Вы идете по следам разлитого вина и находите талисман", R.drawable.braslet, options1_1);
        start_story.way[0].way[0].way[0] =new Situation("Браслет",  "Оберег носила его жена. " +
                "Сделать его мог веленский ворожей, так что нам надо сходить к нему. " +
                "Правда, барон предупредит нас, что ворожей со странностями - а причиной " +
                "этому убийство собственного отца в детстве", R.drawable.koza, options1_1_1);
        start_story.way[0].way[0].way[0].way[1] =new Situation("Ворожей",  "Расчистив путь," +
                "Вы находите ворожея и показываете ему амулет. Старик скажет, что это было сделано для " +
                "Анны для защиты от злых сил, которые пытались овладеть ею", R.drawable.vorozhey, options1_1_1_2);
        start_story.way[0].way[0].way[0].way[1].way[1]=new Situation("Транс",  "Ворожей вошел в транс и говорит, что у Анны был нерожденный и нежеланный ребенок." +
                "В ходе драки с Бароном Анна упала и потеряла ребенка. " +
                "Мертвого закопали без обряда, это стало причиной рождения игоши – проклятое существо", R.drawable.prediction, options1_1_1_2_2);
        start_story.way[0].way[0].way[0].way[1].way[1].way[1] = new Situation("Транс",  "Ведьмак, тебе предстоит сделать выбор: " +
                "снять проклятие с игоши или прекратить его жалкое существование", R.drawable.prediction, options1_3);
        root = start_story.way[0].way[0].way[0].way[1].way[1];
        start_story.way[0].way[0].way[0].way[1].way[1].way[1].way[1] = root;
        start_story.way[0].way[0].way[1] =  start_story.way[0].way[0].way[0];
        start_story.way[0].way[0].way[2] = start_story.way[0].way[0].way[0];
        start_story.way[0].way[1] = new Situation("Отношения в семье", "Ну...Я часто пил и мало что помню в тот вечер, " +
                "повздорили мы, ведьмак. Изменила она мне, ну а я сильно " +
                "разозлился и грохнул её ублюдка. Но я не монстр, меня можно понять.", R.drawable.truth, options1_2);
        start_story.way[0].way[1].way[1] = new Situation("Отношения в семье", "Да иди ты, ведьмак, что ты понимаешь в людской жизни", R.drawable.truth, options1_2_2 );
        start_story.way[0].way[1].way[0] = new Situation("Отношения в семье", "Я знал, что ты меня поймешь, дружище", R.drawable.truth, options1_2_2 );
        start_story.way[0].way[1].way[0].way[1] = start_story.way[0].way[0];
        start_story.way[0].way[1].way[1].way[1] = start_story.way[0].way[0];
        start_story.way[0].way[1].way[2] = start_story.way[0].way[0];
        start_story.way[0].way[2] = new Situation("письмо Тамаре", "Вы " +
                "нашли письмо: \"...Мы как одна семья. Поддерживаем друг друга и " +
                "помогаем друг другу пережить тяжелые времена, справиться с прошлым. " +
                "Ибо у каждого из нас есть свое прошлое. Стоит ли копаться в чужом?\"", R.drawable.letter, options1_3);
        start_story.way[0].way[2].way[1] = start_story.way[0];
        start_story.way[1] = new Situation("Послать", "Кровавый Барон злится и разочаровывается в Вас, " +
                "показывая прощальный жест, но просит еще подумать", R.drawable.fuck, options2);
        start_story.way[1].way[1] = start_story.way[0];
        start_story.way[2] = new Situation("Вызвать на бой", "Кровавый Барон встает в воинственную стойку," +
                "немного пошатываясь, и вызывает весь отряд Велена", R.drawable.fight, options3);
        start_story.way[1].way[2] = start_story.way[2];
        start_story.way[2].way[1] = death_baron;
        current_situation = start_story;
    }
    public void update() {
        for(int i = 0; i < options_v.length; i++){
            options_v[i].setText(current_situation.options[i].text);
            checkVisibility();
        }
        current_situation.setImage(context, image);
        outcome.setText(current_situation.text);
    }
    private void checkVisibility(){
        for(int i = 0 ; i < 3; i ++){
            cards[i].setVisibility(current_situation.options[i].isVisible ? View.VISIBLE : View.INVISIBLE);
            options_v[i].setVisibility(current_situation.options[i].isVisible ? View.VISIBLE : View.INVISIBLE);
        }
    }
    public void go(int num) {
        current_situation = current_situation.way[num - 1];

    }
}
