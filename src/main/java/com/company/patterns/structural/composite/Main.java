package com.company.patterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Компоновщик (Composite)</h1>
 * <h2><font color="#fa8e47">Сложность:⭐⭐</font>⭐<br>
 * <font color="#fa8e47">Популярность:⭐⭐</font>⭐<p></h2>
 * структурный паттерн, который позволяет создавать дерево объектов и работать с ним так же,
 * как и с единичным объектом.
 * <p>
 * <font color="#fa8e47">Применимость:</font> Паттерн Компоновщик встречается в любых задачах, которые связаны с построением дерева.
 * Самый простой пример — составные элементы GUI, которые тоже можно рассматривать как дерево.
 * <p>
 * <font color="#fa8e47">Примеры Компоновщиков в стандартных библиотеках Java:</font>
 * <li>java.awt.Container#add(Component) (обычно применим для компонентов Swing)</li>
 * <li>javax.faces.component.UIComponent#getChildren() (обычно применим для JSF UI)</li>
 * <p>
 * <font color="#fa8e47">Признаки применения паттерна:<br></font>Если из объектов строится древовидная структура, и со всеми объектами дерева,
 * как и с самим деревом работают через общий интерфейс.
 * <p>
 * Существует большое количество программных систем, в которых так или иначе применяются древовидные структуры объектов.
 * большинстве случаев, это всевозможные конструкторы/редакторы, которые позволяют собрать что-то большое (составное) из чего-то более мелкого (листового).
 * При этом, клиент трактует и большое и мелкое как одно и тоже, а система должна различать составные и листовые объекты соответственно.
 * <p>
 * <font color="#fafa47">Составной шаблон может быть реализован везде, где у вас есть иерархическая природа системы или подсистемы,
 * и вы хотите обрабатывать отдельные объекты и составы объектов единообразно.
 * Лист определяет поведение элементов в композиции. Это достигается путем реализации операций, которые поддерживает Компонент.
 *  Лист также наследует методы, которые не обязательно имеют большой смысл для узла листа.
 *  Клиент управляет объектами в композиции через интерфейс Компонента.</font>
 */

// Класс HtmlTag является классом компонента, который определяет все методы, используемые композитом и листовым классом.
// Есть несколько методов, которые должны быть общими в обоих расширенных классах; следовательно,
// эти методы хранятся абстрактно в вышеприведенном классе, чтобы обеспечить их реализацию в дочерних классах.
abstract class HtmlTag {
    public abstract String getTagName();

    public abstract void setStartTag(String tag);

    public abstract void setEndTag(String tag);

    public void setTagBody(String tagBody) {
        throw new UnsupportedOperationException("Current operation is not support for this object");
    }

    public void addChildTag(HtmlTag htmlTag) {
        throw new UnsupportedOperationException("Current operation is not support for this object");
    }

    public void removeChildTag(HtmlTag htmlTag) {
        throw new UnsupportedOperationException("Current operation is not support for this object");
    }

    public List<HtmlTag> getChildren() {
        throw new UnsupportedOperationException("Current operation is not support for this object");
    }

    public abstract void generateHtml();
}


/**
 * Класс HtmlParentElement является составным классом, который реализует методы, такие как addChildTag , removeChildTag , getChildren,
 * которые должны быть реализованы классом, чтобы стать составной структурой.
 */
class HtmlParentElement extends HtmlTag {

    private String tagName;
    private String startTag;
    private String endTag;
    private List<HtmlTag> childrenTag;

    public HtmlParentElement(String tagName) {
        this.tagName = tagName;
        this.startTag = "";
        this.endTag = "";
        this.childrenTag = new ArrayList<>();
    }

    @Override
    public String getTagName() {
        return tagName;
    }

    @Override
    public void setStartTag(String tag) {
        this.startTag = tag;
    }

    @Override
    public void setEndTag(String tag) {
        this.endTag = tag;
    }

    @Override
    public void addChildTag(HtmlTag htmlTag) {
        childrenTag.add(htmlTag);
    }

    @Override
    public void removeChildTag(HtmlTag htmlTag) {
        childrenTag.remove(htmlTag);
    }

    @Override
    public List<HtmlTag> getChildren() {
        return childrenTag;
    }

    @Override
    public void generateHtml() {
        System.out.println(startTag);
        for (HtmlTag tag : childrenTag) {
            tag.generateHtml();
        }
        System.out.println(endTag);
    }
}

/**
 * HtmlElement является листовым классом, и его основная задача заключается в реализации метода операции, который в этом примере является методом generateHtml .
 * Он печатает startTag , необязательно tagBody, если есть, и endTag дочернего элемента.
 */
class HtmlElement extends HtmlTag {
    private String tagName;
    private String startTag;
    private String endTag;
    private String tagBody;

    public HtmlElement(String tagName) {
        this.tagName = tagName;
        this.tagBody = "";
        this.startTag = "";
        this.endTag = "";
    }

    @Override
    public String getTagName() {
        return tagName;
    }

    @Override
    public void setStartTag(String tag) {
        this.startTag = tag;
    }

    @Override
    public void setEndTag(String tag) {
        this.endTag = tag;
    }

    @Override
    public void setTagBody(String tagBody) {
        this.tagBody = tagBody;
    }

    @Override
    public void generateHtml() {
        System.out.println(startTag + "" + tagBody + "" + endTag);
    }

}

class Main {

    public static void main(String[] args) {
        HtmlTag parentTag = new HtmlParentElement("<html>");
        parentTag.setStartTag("<html>");
        parentTag.setEndTag("</html>");

        HtmlTag p1 = new HtmlParentElement("<body>");
        p1.setStartTag("<body>");
        p1.setEndTag("</body>");

        parentTag.addChildTag(p1);

        HtmlTag child1 = new HtmlElement("<p>");
        child1.setStartTag("<p>");
        child1.setEndTag("</p>");
        child1.setTagBody("Testing html tag library");
        p1.addChildTag(child1);

        child1 = new HtmlElement("<p>");
        child1.setStartTag("<p>");
        child1.setEndTag("</p>");
        child1.setTagBody("Paragraph 2");
        p1.addChildTag(child1);

        parentTag.generateHtml();
    }
}
