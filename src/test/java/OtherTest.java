import com.tongbanjie.commons.lang.Result;
import com.tongbanjie.commons.model.BaseQuery;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;

public class OtherTest {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        System.out.println("测试判空结果为:" + list.size() + "result :" + CollectionUtils.isEmpty(list));
        while (true) {
            if (2 == 1) {
                break;
            }
        }
        System.out.println("执行一下！证明跳出循环");

        //testQuery1();
        //testQuery2();
        Result<Void> result = new Result<Void>();
        String reqId = "1";
        //test(reqId, result);
        System.out.println("result的最终结果为" + result.isSuccess());


    }

    private static boolean test(String reqId, Result<Void> result) {
        result.setSuccess(true);
        result.setErrorMsg("已发送成功,无须重发");
        if (result.isSuccess()) {
            return false;
        }
        return true;
    }

    private static void testQuery2() {
        int QUERY_BATCH_NUM = 10;
        int count = 35;
        BaseQuery baseQuery = new BaseQuery();
        //设置每页页数
        baseQuery.setPageSize(QUERY_BATCH_NUM);
        baseQuery.doPage(count);
        // 第一页开始
        Integer pageNo = 1;
        //总页数
        Integer pageCount = baseQuery.getPageCount();
        do {
            baseQuery.setPageNo(pageNo);
            baseQuery.doPage(count);
            System.out.println("分页查询第" + pageNo + "次");
            System.out.print("当前的开始行" + baseQuery.getStartRow());
            System.out.print(", 当前的每页页数" + baseQuery.getPageSize() + "\n");
            pageNo++;
        } while (pageNo <= pageCount);
        System.out.println("跳出循环");
    }

    private static void testQuery1() {
        int count = 35; //假设4页，一共35条记录
        BaseQuery query = new BaseQuery();
        query.setPageSize(10);
        query.doPage(count);
        int pageNo = 1;
        //总页数
        Integer pageCount = query.getPageCount();
        while (true) {
            query.setPageNo(pageNo);
            query.doPage(count);
            System.out.print("当前的开始行" + query.getStartRow());
            System.out.print(", 当前的每页页数" + query.getPageSize() + "\n");
            System.out.println("分页查询第" + pageNo + "次");
            pageNo++;
            if (pageNo > pageCount) {
                break;
            }
        }
        System.out.println("跳出循环");
    }
}
