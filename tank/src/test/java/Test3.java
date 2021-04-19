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
public class Test3 {

    @Test
    public void TestChain() {
        Request request = new Request();
        request.setStr("request");

        Response response = new Response();
        response.setStr("response");

        FilterChain filter = new FilterChain();

        filter.add(new HtmlFilter()).add(new NumberFilter());

        filter.doFilter(request, response, filter);

        System.out.println(request + "====" + response);

    }


    public interface Filter {
        void doFilter(Request request, Response response, FilterChain filterChain);
    }

    public class FilterChain implements Filter {
        ArrayList<Filter> filters = new ArrayList<>();
        int index = 0;

        public FilterChain add(Filter f) {
            filters.add(f);
            return this;
        }

        @Override
        public void doFilter(Request request, Response response, FilterChain filterChain) {
            if (index == filters.size()) return;
            Filter filter = filters.get(index);
            index++;
            filter.doFilter(request, response, filterChain);

            //for (Filter f : filters) {
            //    f.doFilter(request, null);
            //    System.out.println(request + "====" + response);
            //}
            //for (int i = filters.size() -1; i >= 0; i--) {
            //    filters.get(i).doFilter(null, response);
            //    System.out.println(request + "====" + response);
            //}
        }

    }

    public class HtmlFilter implements Filter {

        @Override
        public void doFilter(Request request, Response response, FilterChain filterChain) {
            request.setStr(request.getStr() + "1");
            filterChain.doFilter(request, response, filterChain);
            response.setStr(response.getStr() + "a");
        }
    }

    public class NumberFilter implements Filter {

        @Override
        public void doFilter(Request request, Response response, FilterChain filterChain) {
            request.setStr(request.getStr() + "2");
            filterChain.doFilter(request, response, filterChain);
            response.setStr(response.getStr() + "b");
        }
    }

    public class Request {
        private String str;

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            return "Request{" +
                    "str='" + str + '\'' +
                    '}';
        }
    }

    public class Response {
        private String str;

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "str='" + str + '\'' +
                    '}';
        }
    }


}
