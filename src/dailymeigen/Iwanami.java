//名言テキストパッケージ
package dailymeigen;

import java.io.IOException;
import java.net.URL;
import java.util.List;

//htmlパーサのインポート
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.Source;

/**
 * dailymeigen.Iwanami
 * @author watanabe-yuu
 */
public class Iwanami {
    private static final String BR = System.getProperty("line.separator");
/**
 * main
     * @param args
 */
    public static void main(String[] args) {
        try {
            System.out.print(meigen("http://www.iwanami.co.jp/meigen/heute.html"));
        } catch (IOException e) {
            System.out.print("exception:" + e.toString());
        }

    }
/**
 * meigen
 * @param sourceUrlString
 * @return meigenString
 * @throws IOException 
 */
    private static String meigen(String sourceUrlString) throws IOException {

        boolean meigenFound = false;
        StringBuilder SB = new StringBuilder();
        SB.append("【今日の名言(岩波文庫より)】※http://www.iwanami.co.jp/meigen/").append(BR);

        Source source = new Source(new URL(sourceUrlString));
        List<Element> elementList = source.getAllElements();
        for (Element element : elementList) {

            if (!meigenFound && element.getStartTag().toString().equals("<div class=\"witticism\">")) {
                meigenFound = true;
                SB.append("　").append(element.getTextExtractor()).append(BR);
            }

            if (meigenFound && element.getStartTag().toString().equals("<div class=\"source\">")) {
                SB.append("　（出典:").append(element.getTextExtractor()).append("）").append(BR);
            }

        }
        if (!meigenFound) {
            return "名言が見つかりませんでした";
        }
        return SB.toString();
    }
}
