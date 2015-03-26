package wikipedia.domain;

public abstract class Element
{
    private String title;

    public Element()
    {
        title = null;
    }

    public Element(String title)
    {
        this.title = title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
}
