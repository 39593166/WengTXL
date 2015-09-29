package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * @ClassName: ViewHolderHelper
 * @Description:通用的viewholder辅助类
 */
public class ViewHolderHelper {
	private final SparseArray<View> mViews;
	private int mPosition;
	private View mConvertView;
	Context context;

	private ViewHolderHelper(Context context, ViewGroup parent, int layoutId,
			int position) {
		this.mPosition = position;
		this.context = context;
		this.mViews = new SparseArray<View>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
				false);
		mConvertView.setTag(this);
	}

	/**
	 * 拿到一个ViewHolder对象
	 * 
	 * @param context
	 * @param convertView
	 * @param parent
	 * @param layoutId
	 * @param position
	 * @return
	 */
	public static ViewHolderHelper get(Context context, View convertView,
			ViewGroup parent, int layoutId, int position) {
		if (convertView == null) {
			return new ViewHolderHelper(context, parent, layoutId, position);
		}
		return (ViewHolderHelper) convertView.getTag();
	}

	public View getConvertView() {
		return mConvertView;
	}

	/**
	 * 通过控件的Id获取对于的控件，如果没有则加入views
	 * 
	 * @param viewId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int viewId) {
		View view = mViews.get(viewId);
		if (view == null) {
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}

	/**
	 * 为TextView设置字符串
	 * 
	 * @param viewId
	 * @param text
	 * @return
	 */
	public ViewHolderHelper setText(int viewId, String text) {
		TextView view = getView(viewId);
		view.setText(text);
		return this;
	}

	/**
	 * 为ImageView设置图片
	 * 
	 * @param viewId
	 * @param drawableId
	 * @return
	 */
	public ViewHolderHelper setImageResource(int viewId, int drawableId) {
		ImageView view = getView(viewId);
		view.setImageResource(drawableId);
		return this;
	}

	/**
	 * 为ImageView设置图片
	 * 
	 * @param viewId
	 * @param drawableId
	 * @return
	 */
	public ViewHolderHelper setImageBitmap(int viewId, Bitmap bm) {
		ImageView view = getView(viewId);
		view.setImageBitmap(bm);
		return this;
	}

	/**
	 * 为ImageView设置图片
	 * 
	 * @param viewId
	 * @param drawableId
	 * @return
	 */
	public ViewHolderHelper setImageByUrl(int viewId, String url,
			int failRecource) {
		final ImageView img = (ImageView) getView(viewId);
		Picasso.with(context).load(url).error(failRecource).into(img);
		return this;
	}

	/**
	 * 为view设置点击事件
	 * 
	 * @param viewId
	 * @param lis
	 * @return
	 */
	public ViewHolderHelper setClickListener(int viewId, OnClickListener lis) {
		View view = getView(viewId);
		view.setOnClickListener(lis);
		return this;
	}

	/**
	 * checkBox设置选择变化事件
	 * 
	 * @param viewId
	 * @param lis
	 * @return
	 */
	public ViewHolderHelper setChecBoxChangeLis(int viewId,
			OnCheckedChangeListener lis) {
		CheckBox view = (CheckBox) getView(viewId);
		view.setOnCheckedChangeListener(lis);
		return this;
	}
	/**
	 * 显示或者隐藏控件
	 * @param viewId
	 * @param isVisiable
	 * @return
	 */
	public ViewHolderHelper VisbaleView(int viewId,boolean isVisiable) {
		View view = getView(viewId);
		view.setVisibility(isVisiable?View.VISIBLE:View.GONE);
		return this;
	}

	public int getPosition() {
		return mPosition;
	}

}
