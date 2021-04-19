import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * <strong>Description : TODO</strong><br>
 * <strong>Create on : 2021/3/18 15:16<br>
 * </strong>
 * <p>
 *
 * @author LiXuyang <br>
 * @version <strong> </strong><br>
 * <br>
 * <strong>修改历史:</strong><br>
 * 修改人 | 修改日期 | 修改描述<br>
 * -------------------------------------------<br>
 * <br>
 * <br>
 * @email arnoldlee9527@163.com<br>
 */
public class Test2 {

    @Test
    public void TestChain() {
        Msg msg = new Msg();
        msg.setStr("hello <script> baidu.com 678 a 678");

        FilterChain fc = new FilterChain();
        fc.add(new HtmlFilter()).add(new NumberFilter());

        FilterChain fc2 = new FilterChain();
        fc2.add(new AddressFilter());

        fc.add(fc2);

        fc.doFilter(msg);
        //fc2.doFilter(msg);

        //ArrayList<Filter> filters = new ArrayList<>();
        //filters.add(new HtmlFilter());
        //filters.add(new NumberFilter());
        //for(Filter f : filters) {
        //    f.doFilter(msg);
        //}

        //Filter htmlFilter = new HtmlFilter();
        //htmlFilter.doFilter(msg);
        //
        //Filter numberFilter = new NumberFilter();
        //numberFilter.doFilter(msg);

        System.out.println(msg.getStr());

    }

    public interface Filter {
        boolean doFilter(Msg msg);
    }

    public class FilterChain implements Filter {
        ArrayList<Filter> filters = new ArrayList<>();

        public FilterChain add(Filter f) {
            filters.add(f);
            return this;
        }

        @Override
        public boolean doFilter(Msg msg) {
            for (Filter f : filters) {
                if (!f.doFilter(msg)) return false;
            }
            return true;
        }

    }

    public class HtmlFilter implements Filter {

        @Override
        public boolean doFilter(Msg msg) {
            String str = msg.getStr();
            str = str.replace("<", "[");
            str = str.replace(">", "]");

            msg.setStr(str);
            return true;
        }
    }

    public class NumberFilter implements Filter {

        @Override
        public boolean doFilter(Msg msg) {
            String str = msg.getStr();
            str = str.replace("678", "567");

            msg.setStr(str);
            return false;
        }
    }

    public class AddressFilter implements Filter {

        @Override
        public boolean doFilter(Msg msg) {
            String str = msg.getStr();
            str = str.replace("baidu.com", "http://www.baidu.com");

            msg.setStr(str);
            return true;
        }
    }

    public class Msg {
        private String str;

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            return "Msg{" +
                    "str='" + str + '\'' +
                    '}';
        }
    }


}
