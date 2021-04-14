package com.jackxue.niochat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Rom {
    private String romName;
    private AtomicInteger memberNumbers = new AtomicInteger(0);
    private List<Member> memberList = new ArrayList<>();

    public Rom() {
    }

    public Rom(String romName) {
        this.romName = romName;
    }

    public void addMember(Member member){
        memberList.add(member);
        int count = memberNumbers.incrementAndGet();
        System.out.println("add member:" + member.getUserName() + " total:" + count);
    }

    public void removeMemberByChannel(){
        int members = memberNumbers.decrementAndGet();
        System.out.println("member left count:" + members);
    }


    public String getRomName() {
        return romName;
    }

    public void setRomName(String romName) {
        this.romName = romName;
    }

    public AtomicInteger getMemberNumbers() {
        return memberNumbers;
    }

    public void setMemberNumbers(AtomicInteger memberNumbers) {
        this.memberNumbers = memberNumbers;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }


}
