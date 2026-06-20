package ticket.booking.entities;

import java.util.List;

import ticket.booking.services.TrainService;

public class SeatLayout {
	private List<List<Integer>> layout;
	
	public SeatLayout() {}
	
    public SeatLayout(List<List<Integer>> layout) {
        this.layout = layout;
    }

	public List<List<Integer>> getLayout() {
		return layout;
	}

	public void setLayout(List<List<Integer>> layout) {
		this.layout = layout;
	}
	
	public void displaySeats() {
		for (List<Integer> row: layout){
            for (Integer val: row){
                System.out.print(val +  " ");
            }
            System.out.println();
        }
	}
	
	public boolean bookSeat(int row, int col) {
         if (row >= 0 && row < layout.size() && col >= 0 && col < layout.get(row).size()) {
             if (layout.get(row).get(col) == 0) {
            	 layout.get(row).set(col, 1);
            	 return true;
             }
         }
		 return false;
	}
}
