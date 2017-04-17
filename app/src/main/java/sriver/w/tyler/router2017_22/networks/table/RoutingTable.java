package sriver.w.tyler.router2017_22.networks.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sriver.w.tyler.router2017_22.networks.tablerecord.RoutingRecord;
import sriver.w.tyler.router2017_22.networks.tablerecord.TableRecord;
import sriver.w.tyler.router2017_22.support.LabException;

/**
 * Created by tyler.w.sriver on 4/12/17.
 *
 * Table class to hold routes and supporting methods
 */
public class RoutingTable extends TimedTable {

    // -- Methods
    // --------------------------------------------------------------

    /**
     * Constructor
     */
    public RoutingTable(){
        super();
    }

    /**
     * Add the record if not exists
     * @param newEntry TableRecord
     */
    public void addNewRoute(TableRecord newEntry){
        RoutingRecord newRoutingRecord = (RoutingRecord) newEntry;
        boolean isUpdated = false;
        for (int i = 0; i < table.size(); i++) {
            if (table.get(i).getKey().compareTo(newRoutingRecord.getKey()) == 0) {
                RoutingRecord temp = (RoutingRecord) table.get(i);
                if (temp.getDistance() == newRoutingRecord.getDistance()) {
                    // Update time if same route
                    ((RoutingRecord) table.get(i)).updateTime();
                } else {
                    // Remove and replace if different
                    removeItem(temp.getKey());
                    table.add(newRoutingRecord);
                    notifyObservers();
                }
                isUpdated = true;
            }
        }

        //If not found in the table add it
        if(!isUpdated) {
            table.add(newRoutingRecord);
            notifyObservers();
        }
    }

    /**
     * Remove record if exists
     * @param recordToRemove TableRecord
     */
    public void removeItem(TableRecord recordToRemove) {
        super.removeItem(recordToRemove.getKey());
        notifyObservers();
    }

    /**
     * Return LL3P address of next hop
     * @param network Integer
     * @return int
     */
    public int getNextHop(Integer network) throws LabException{
        for(int i = 0; i < table.size(); i++){
            RoutingRecord record = (RoutingRecord) table.get(i);
            if(record.getNetworkNumber() == network){
                return record.getNextHop();
            }
        }
        throw new LabException("Record not found");
    }

    /**
     * Get all routes that don't have the given ll3paddress
     * @param ll3pAddress Integer
     * @return List
     */
    public List<RoutingRecord> getRoutesExcluding(Integer ll3pAddress){
        List<RoutingRecord> returnList = new ArrayList<>();
        for (TableRecord record: table) {
            RoutingRecord routingRecord = (RoutingRecord) record;
            if(routingRecord.getNextHop() != ll3pAddress)
                returnList.add(routingRecord);
        }
        return returnList;
    }

    /**
     * Remove records that match given ll3paddress
     * @param ll3pAddress Integer
     */
    public void removeRoutesFrom(Integer ll3pAddress){
        for(int i = 0; i < table.size(); i++) {
            RoutingRecord record = (RoutingRecord) table.get(i);
            if(record.getNextHop() == ll3pAddress){
                removeItem(record);
            }
        }
    }

    /**
     * This returns the best route to each known remote network
     * @return List
     */
    public List<RoutingRecord> getBestRoutes() {
        HashMap<Integer,Integer> hashMap = new HashMap<>();

        for(int i = 0; i < table.size(); i++) {
            RoutingRecord temp = (RoutingRecord) table.get(i);

            // Store key and distance
            int key = temp.getNetworkNumber();
            int dist = temp.getDistance();

            // Compare distances
            if(hashMap.containsKey(key)) {
                if(hashMap.get(key) > dist) {
                    hashMap.put(key, dist);
                }
            } else {
                hashMap.put(key, dist);
            }
        }

        List<RoutingRecord> bestRoutes = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            RoutingRecord temp = getRecordNetDist(entry.getKey(), entry.getValue());
            bestRoutes.add(temp);
        }
        return bestRoutes;
    }

    /**
     * Retrieve the record that has the given network and distance
     * @param network int
     * @param distance distance
     * @return RoutingRecord
     */
    private RoutingRecord getRecordNetDist(int network, int distance){
        for(int i = 0; i < table.size(); i++) {
            RoutingRecord record = (RoutingRecord) table.get(i);
            if(record.getDistance() == distance && record.getNetworkNumber() == network) {
                return record;
            }
        } return null;
    }

    /**
     * This returns the best route for the specified remote network
     * @param network Integer
     * @return RoutingRecord
     */
    public RoutingRecord getBestRoute(Integer network) throws LabException {
        List<RoutingRecord> bestRecords = getBestRoutes();
        for (RoutingRecord record:bestRecords) {
            if(record.getNetworkNumber() == network) {
                return record;
            }
        } throw new LabException("No Route Found");
    }


    /**
     * Add the new routes based on known update criteria
     * @param routes List
     */
    public boolean addRoutes(List<RoutingRecord> routes){
        for (RoutingRecord record:routes) {
            addNewRoute(record);
        }
        return false;
    }
}
