package SDE.BinarySearch;

public class AllocateBooks {
    public static void main(String[] args) {
        // books with pages count
        int[] books = {12, 34, 67, 90};
        // no of students
        int noOfStudents = 2;
        System.out.println(binarySearch(books, noOfStudents));
    }

    public static int binarySearch(int[] books, int noOfStudents){

        if(books.length > noOfStudents){
            return -1;
        }

        int low = 0;
        int high = 0;
        for (int book : books){
            low = Math.max(low, book);
            high += book;
        }
        int mid;
        while (low <= high){
            mid = low + high >> 1;
            if(isAllocationPossible(mid, books, noOfStudents)){
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static boolean isAllocationPossible(int barrier, int[] books, int noOfStudents) {
        int allocatedStudents = 1;
        int pages = 0;
        for (int book : books) {
            if (book > barrier)
                return false;
            if (pages + book > barrier) {
                allocatedStudents += 1;
                pages = book;
            }else {
                pages += book;
            }
        }
        return allocatedStudents <= noOfStudents;
    }
}
