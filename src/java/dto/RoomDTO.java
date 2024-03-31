/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Admin
 */
public class RoomDTO {
    
    private String roomId;
    private BoardingDTO boarding;
    private boolean status;

    public RoomDTO() {
    }

    public RoomDTO(String roomId, BoardingDTO boarding, boolean status) {
        this.roomId = roomId;
        this.boarding = boarding;
        this.status = status;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public BoardingDTO getBoarding() {
        return boarding;
    }

    public void setBoarding(BoardingDTO boarding) {
        this.boarding = boarding;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
  
}