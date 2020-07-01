import com.google.common.collect.Lists;
import com.zuoqiang.test.customer.orm.User;
import com.zuoqiang.test.tools.commons.BaseQuery;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

public class OtherTest {
    public static void main(String[] args) {
      /*  ArrayList<String> list = new ArrayList<>();
        System.out.println("测试判空结果为:" + list.size() + "result :" + CollectionUtils.isEmpty(list));
        while (true) {
            if (2 == 1) {
                break;
            }
        }
        System.out.println("执行一下！证明跳出循环");*/
        //testQuery1();
        testQuery2();

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


    public static void query3() {
        List<User> accountSummaryList = null;
        int pageNumber = 0;
        //批处理数量
        int patchSize = 50;
        do {
            pageNumber++;
            System.out.print(", 当前的每页页数" + patchSize + "\n");
            System.out.println("分页查询第" + pageNumber + "次");
            //此处应该为查询出来的数据
            accountSummaryList = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(accountSummaryList)) {
                accountSummaryList.stream().forEach(
                        e -> {
                            System.out.println("执行对应业务！");
                        }
                );
            }
        } while (CollectionUtils.isNotEmpty(accountSummaryList) && accountSummaryList.size() == patchSize);
    }


}