package com.vitaliyr.app.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;

import com.vitaliyr.app.entity.Flights;
import com.vitaliyr.app.entity.Reserves;
import com.vitaliyr.app.service.CustomerService;

@Named("cart")
@Scope("session")
public class CartBean {

	@Inject
	CustomerBean customerBean;

	@Inject
	CustomerService customerService;

	private List<Flights> selectedList;
	private String customerMail;
	private String customerName;
	private List<Reserves> toOrder;
	private String orderNumber;

	/*
	 * Info on top
	 */
	private int cartItemCount = 0;
	private double cartTotalPrice = 0;

	/*
	 * Cart operations
	 */
	public String selectToCart() {
		selected: for (Flights f : customerBean.getFoundList()) {
			if (f.isSelected()) {
				for (Reserves r : toOrder) {
					if (r.getFlightId() == f.getId()) {
						f.setSelected(false);
						continue selected;
					}
				}
				selectedList.add(f);
				f.setSelected(false);
			}
		}
		createCartReserves();
		selectedList.clear();
		return null;
	}

	private void createCartReserves() {
		for (Flights f : selectedList) {
			Reserves newRes = new Reserves(f.getId(), f.getFcode(),
					f.getFdate(), f.getDesCity(), f.getDesPort(), 1,
					f.getTicketPrice());
			toOrder.add(newRes);
			++cartItemCount;
		}
		calculateTotal();
	}

	/*
	 * Cart dataTable operations
	 */
	public String deleteReserve(Reserves r) {
		toOrder.remove(r);
		--cartItemCount;
		calculateTotal();
		return null;

	}

	public String editReserve(Reserves r) {
		r.setEditable(true);
		return null;
	}

	public String saveCartItem(Reserves r) {

		if (!customerService.checkAvailableSeats(r.getFlightId(),
				r.getTicketCount())) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error. No tickets available. Try again. ",
							"Error. No tickets available. Try again."));
			return null;
		}

		r.setEditable(false);
		calculateTotal();
		return null;

	}

	/*
	 * Calculates temporary price
	 */
	public void calculateTotal() {
		double temp = 0;
		for (Reserves r : toOrder) {
			temp += r.getTicketCount() * r.getTicketPrice();
		}
		cartTotalPrice = temp;
	}

	/*
	 * Order creation
	 */
	public String createOrder() {
		String result = customerService.addNewOrder(toOrder, cartTotalPrice,
				customerMail, customerName);
		if (result == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error. No tickets available. Try again.",
							"Error. No tickets available. Try again."));
			return null;
		}

		orderNumber = result;
		return "orderInfo";
	}

	/*
	 * Cleanup after receiving order
	 */
	public void cleanUp() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		session.removeAttribute("home");
		session.removeAttribute("cart");
	}

	/*
	 * Getters & Setters
	 */
	public List<Flights> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(List<Flights> selectedList) {
		this.selectedList = selectedList;
	}

	public String getCustomerMail() {
		return customerMail;
	}

	public void setCustomerMail(String customerMail) {
		this.customerMail = customerMail;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<Reserves> getToOrder() {
		return toOrder;
	}

	public void setToOrder(List<Reserves> toOrder) {
		this.toOrder = toOrder;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getCartItemCount() {
		return cartItemCount;
	}

	public void setCartItemCount(int cartItemCount) {
		this.cartItemCount = cartItemCount;
	}

	public double getCartTotalPrice() {
		return cartTotalPrice;
	}

	public void setCartTotalPrice(double cartTotalPrice) {
		this.cartTotalPrice = cartTotalPrice;
	}

	public CartBean() {
		super();
		selectedList = new ArrayList<Flights>();
		toOrder = new ArrayList<Reserves>();
	}

}
